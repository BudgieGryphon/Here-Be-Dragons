package com.budgiegryphon.herebedragons.client.entity.render;

import java.util.List;

import javax.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mod.azure.azurelib.resource.Pixel;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.resources.data.IMetadataSectionSerializer;
import net.minecraft.util.JSONUtils;

public class CustomLayerMeta {
    public static final IMetadataSectionSerializer<CustomLayerMeta> DESERIALIZER = new IMetadataSectionSerializer<CustomLayerMeta>() {
        @Override
        public String getMetadataSectionName() {
            return "newsections";
        }

        @Override
        public CustomLayerMeta fromJson(JsonObject json) {
            List<Pixel> pixels = fromSections(JSONUtils.getAsJsonArray(json, "sections", null));

            if (pixels.isEmpty())
                throw new JsonParseException("Empty newlayer sections file. Must have at least one section!");

            return new CustomLayerMeta(pixels);
        }

        /**
         * Generate a {@link Pixel} collection from the "sections" array of the mcmeta file
         */
        private List<Pixel> fromSections(@Nullable JsonArray sectionsArray) {
            if (sectionsArray == null)
                return new ObjectArrayList<>();

            List<Pixel> pixels = new ObjectArrayList<>();

            for (JsonElement element : sectionsArray) {
                if (!(element instanceof JsonObject))
                    throw new JsonParseException("Invalid newsections json format, expected a JsonObject, found: " + element.getClass());

                int x1 = JSONUtils.getAsInt((JsonObject) element, "x1", JSONUtils.getAsInt((JsonObject) element, "x", 0));
                int y1 = JSONUtils.getAsInt((JsonObject) element, "y1", JSONUtils.getAsInt((JsonObject) element, "y", 0));
                int x2 = JSONUtils.getAsInt((JsonObject) element, "x2", JSONUtils.getAsInt((JsonObject) element, "w", 0) + x1);
                int y2 = JSONUtils.getAsInt((JsonObject) element, "y2", JSONUtils.getAsInt((JsonObject) element, "h", 0) + y1);
                int alpha = JSONUtils.getAsInt((JsonObject) element, "alpha", JSONUtils.getAsInt((JsonObject) element, "a", 0));

                if (x1 + y1 + x2 + y2 == 0)
                    throw new IllegalArgumentException("Invalid newsections section object, section must be at least one pixel in size");

                for (int x = x1; x <= x2; x++) {
                    for (int y = y1; y <= y2; y++) {
                        pixels.add(new Pixel(x, y, alpha));
                    }
                }
            }

            return pixels;
        }
    };

    private final List<Pixel> pixels;

    public CustomLayerMeta(List<Pixel> pixels) {
        this.pixels = pixels;
    }

    /**
     * Generate the NewLayer pixels list from an existing image resource, instead of using the .png.mcmeta file
     */
    public static CustomLayerMeta fromExistingImage(NativeImage newLayer) {
        List<Pixel> pixels = new ObjectArrayList<>();

        for (int x = 0; x < newLayer.getWidth(); x++) {
            for (int y = 0; y < newLayer.getHeight(); y++) {
                int color = newLayer.getPixelRGBA(x, y);

                if (color != 0)
                    pixels.add(new Pixel(x, y, NativeImage.getA(color)));
            }
        }

        if (pixels.isEmpty())
            throw new IllegalStateException("Invalid new layer texture provided, must have at least one pixel!");

        return new CustomLayerMeta(pixels);
    }

    /**
     * Create a new mask image based on the pre-determined pixel data
     */
    public void createImageMask(NativeImage originalImage, NativeImage newImage) {
        for (Pixel pixel : this.pixels) {
            int color = originalImage.getPixelRGBA(pixel.x, pixel.y);

            if (pixel.alpha > 0)
                color = NativeImage.combine(pixel.alpha, NativeImage.getB(color), NativeImage.getG(color), NativeImage.getR(color));

            newImage.setPixelRGBA(pixel.x, pixel.y, color);
            originalImage.setPixelRGBA(pixel.x, pixel.y, 0);
        }
    }

}
