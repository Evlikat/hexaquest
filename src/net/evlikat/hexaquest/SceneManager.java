package net.evlikat.hexaquest;

import android.graphics.Color;
import android.graphics.Typeface;
import net.evlikat.hexaquest.view.GameView;
import net.evlikat.hexaquest.view.MainMenuView;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;

/**
 *
 * @author Roman Prokhorov
 * @version 1.0 (May, 26, 2015)
 */
public class SceneManager {

    private GameView currentView;

    private final MainActivity activity;
    private final Engine engine;
    private final Camera camera;
    private final TextureStorage textures;
    private final IFont font;
    //
    private MainMenuView menuView;

    public SceneManager(MainActivity activity, Engine engine, Camera camera, TextureStorage textures) {
        this.activity = activity;
        this.engine = engine;
        this.camera = camera;
        this.textures = textures;
        BitmapTextureAtlas fontTexture = new BitmapTextureAtlas(engine.getTextureManager(), 2048, 2048, TextureOptions.BILINEAR);
        this.font = new Font(engine.getFontManager(), fontTexture,
                Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL), 48, true, Color.WHITE);
        this.font.load();
        this.currentView = getMainMenuView();
    }

    public void updateCurrentView() {
        currentView.update();
    }

    public GameView getCurrentView() {
        return currentView;
    }

    public void setCurrentView(GameView currentView) {
        this.currentView = currentView;
        this.engine.setScene(currentView.getScene());
    }

    public final MainMenuView getMainMenuView() {
        if (menuView == null) {
            menuView = new MainMenuView(engine, camera, textures, font, this);
            menuView.populate();
        }
        return menuView;
    }

    public void quit() {
        activity.finish();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
