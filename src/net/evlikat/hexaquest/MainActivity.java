package net.evlikat.hexaquest;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.util.FPSLogger;
import org.andengine.ui.activity.BaseGameActivity;

/**
 * Created by RSProkhorov on 26.05.2015.
 */
public class MainActivity extends BaseGameActivity {

    private static final int CAMERA_WIDTH = 1920;
    private static final int CAMERA_HEIGHT = 1080;

    private Camera camera;
    private TextureStorage textureStorage;
    private SceneManager sceneManager;

    @Override
    public EngineOptions onCreateEngineOptions() {
        this.camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        return new EngineOptions(
                true,
                ScreenOrientation.LANDSCAPE_FIXED,
                new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
                this.camera);
    }

    @Override
    public void onCreateResources(OnCreateResourcesCallback onCreateResourcesCallback) throws Exception {
        this.textureStorage = new TextureStorage(getTextureManager(), getAssets());
        this.sceneManager = new SceneManager(this, getEngine(), camera, textureStorage);

        onCreateResourcesCallback.onCreateResourcesFinished();
    }

    @Override
    public void onCreateScene(OnCreateSceneCallback onCreateSceneCallback) throws Exception {
        this.mEngine.registerUpdateHandler(new FPSLogger());
        final Scene scene = this.sceneManager.getCurrentView().getScene();

        this.mEngine.registerUpdateHandler(new TimerHandler(0.05f, true, new ITimerCallback() {

            public void onTimePassed(TimerHandler pTimerHandler) {
                sceneManager.updateCurrentView();
            }
        }));
        onCreateSceneCallback.onCreateSceneFinished(scene);
    }

    @Override
    public void onPopulateScene(Scene scene, OnPopulateSceneCallback onPopulateSceneCallback) throws Exception {
        onPopulateSceneCallback.onPopulateSceneFinished();
    }
}
