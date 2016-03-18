package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingBox;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.light.Light;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    Tank tank ;
     World world;
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {

 
        
         tank = new Tank(this);
         world = new World(this);
        //
      // rootNode.attachChild(tank.tankNode);
       Texture skyText = assetManager.loadTexture("Textures/sky.jpg");
        Spatial sky = SkyFactory.createSky(assetManager,skyText,true);
         rootNode.attachChild(sky);
        
         initLight();
         keyControl();
     // rootNode.attachChild(geom);
    }
    
    
    private void initLight(){
    
          DirectionalLight sun = new DirectionalLight();
          sun.setDirection(new Vector3f(-1,-1,-1).normalizeLocal());
          rootNode.addLight(sun);
    
    }

     public void   keyControl(){
      inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_J));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_L));
        inputManager.addMapping("Ahead", new KeyTrigger(KeyInput.KEY_I));
        inputManager.addMapping("Back", new KeyTrigger(KeyInput.KEY_K));
        // add an analog listener, which reacts to the specified mapped strings
        inputManager.addListener(tank, "Left", "Right","Ahead","Back");
 
     }
     
     
     
    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
