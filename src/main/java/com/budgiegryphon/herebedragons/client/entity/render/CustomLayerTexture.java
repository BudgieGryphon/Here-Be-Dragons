package com.budgiegryphon.herebedragons.client.entity.render;

import com.mojang.blaze3d.systems.IRenderCall;
import mod.azure.azurelib.AzureLib;
import mod.azure.azurelib.cache.texture.GeoAbstractTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.client.renderer.texture.Texture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.data.TextureMetadataSection;
import net.minecraft.resources.IResource;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class CustomLayerTexture extends GeoAbstractTexture {
    private static final String TESTAPPEND = "custom";
    protected final ResourceLocation textureBase;
    protected final ResourceLocation newLayer;
    public CustomLayerTexture(ResourceLocation originalLocation, ResourceLocation location) {
        this.textureBase = originalLocation;
        this.newLayer = location;
    }

    static class CustomLayerRenderType extends RenderType {
        public CustomLayerRenderType(String p_i225992_1_, VertexFormat p_i225992_2_, int p_i225992_3_, int p_i225992_4_, boolean p_i225992_5_, boolean p_i225992_6_, Runnable p_i225992_7_, Runnable p_i225992_8_) {
            super(p_i225992_1_, p_i225992_2_, p_i225992_3_, p_i225992_4_, p_i225992_5_, p_i225992_6_, p_i225992_7_, p_i225992_8_);
        }
        public static RenderType customlayer(ResourceLocation texture) {
            return RenderType.create("custom_layer", DefaultVertexFormats.NEW_ENTITY, 7, 256, State.builder().setAlphaState(RenderType.DEFAULT_ALPHA).setCullState(RenderType.NO_CULL).setTextureState(new TextureState(texture, false, false)).setTransparencyState(RenderType.TRANSLUCENT_TRANSPARENCY).setOverlayState(RenderType.OVERLAY).createCompositeState(true));
        }
    }
    protected static ResourceLocation getCustomLayerTexture(ResourceLocation resource) {
        ResourceLocation path = appendToPath(resource, TESTAPPEND);
        generateTexture(path, textureManager -> textureManager.register(path, new CustomLayerTexture(resource, path)));
        return path;
    }
    @Nullable
    @Override
    protected IRenderCall loadTexture(IResourceManager resourceManager, Minecraft mc) throws IOException {
        Texture originalTexture;

        try {
            originalTexture = mc.submit(() -> mc.getTextureManager().getTexture(this.textureBase)).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new IOException("Failed to load original texture: " + this.textureBase, e);
        }

        IResource textureBaseResource = resourceManager.getResource(this.textureBase);
        NativeImage baseImage = originalTexture instanceof DynamicTexture ? ((DynamicTexture) originalTexture).getPixels() : NativeImage.read(textureBaseResource.getInputStream());
        NativeImage newImage = null;
        TextureMetadataSection textureBaseMeta = textureBaseResource.getMetadata(TextureMetadataSection.SERIALIZER);
        boolean blur = textureBaseMeta != null && textureBaseMeta.isBlur();
        boolean clamp = textureBaseMeta != null && textureBaseMeta.isClamp();

        try {
            IResource newLayerResource = resourceManager.getResource(this.newLayer);
            CustomLayerMeta newLayerMeta = null;

            if (newLayerResource != null) {
                newImage = NativeImage.read(newLayerResource.getInputStream());
                newLayerMeta = CustomLayerMeta.fromExistingImage(newImage);
            } else {
                CustomLayerMeta meta = textureBaseResource.getMetadata(CustomLayerMeta.DESERIALIZER);

                if (meta != null) {
                    newLayerMeta = meta;
                    newImage = new NativeImage(baseImage.getWidth(), baseImage.getHeight(), true);
                }
            }

            if (newLayerMeta != null) {
                newLayerMeta.createImageMask(baseImage, newImage);

                if (!FMLEnvironment.production) {
                    printDebugImageToDisk(this.textureBase, baseImage);
                    printDebugImageToDisk(this.newLayer, newImage);
                }
            }
        } catch (IOException e) {
            AzureLib.LOGGER.warn("Resource failed to open for customlayer meta: {}", this.newLayer, e);
        }

        NativeImage mask = newImage;

        if (mask == null)
            return null;

        return () -> {
            uploadSimple(getId(), mask, blur, clamp);

            if (originalTexture instanceof DynamicTexture) {
                ((DynamicTexture) originalTexture).upload();
            } else {
                uploadSimple(originalTexture.getId(), baseImage, blur, clamp);
            }
        };
    }
    public static RenderType getRenderType(ResourceLocation texture) {
        return CustomLayerRenderType.customlayer(getCustomLayerTexture(texture));
    }
    
}
