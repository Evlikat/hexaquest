package net.evlikat.hexaquest;

import android.content.res.AssetManager;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.util.adt.io.in.IInputStreamOpener;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by RSProkhorov on 26.05.2015.
 */
public final class TextureStorage {

    private final TextureManager textureManager;
    private final AssetManager assetManager;

    private final Map<Texture, TextureRegion> regionMap = new EnumMap<Texture, TextureRegion>(Texture.class);

    public TextureStorage(TextureManager textureManager, AssetManager assetManager) {
        this.textureManager = textureManager;
        this.assetManager = assetManager;
    }

    public TextureRegion getTexture(Texture texture) {
        TextureRegion region = regionMap.get(texture);
        if (region == null) {
            region = loadTexture(textureManager, assetManager, texture.getFilename());
            regionMap.put(texture, region);
        }
        return region;
    }

    private TextureRegion loadTexture(final TextureManager textureManager, final AssetManager assetManager, final String id) {
        try {
            ITexture texture = new BitmapTexture(textureManager, new IInputStreamOpener() {
                @Override
                public InputStream open() throws IOException {
                    return assetManager.open("gfx/" + id + ".png");
                }
            });
            texture.load();
            return TextureRegionFactory.extractFromTexture(texture);
        } catch (IOException ex) {
            throw new RuntimeException("Can not load texture for: " + id);
        }
    }

}
