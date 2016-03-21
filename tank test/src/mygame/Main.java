package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
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
     BulletAppState bullet;
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        world = new World(this);
        initPhysics();     
         tank = new Tank(this);
         
        //
      // rootNode.attachChild(tank.tankNode);
       Texture skyText = assetManager.loadTexture("Textures/sky.jpg");
        Spatial sky = SkyFactory.createSky(assetManager,skyText,true);
         rootNode.attachChild(sky);
        initCam();
         initLight();
         keyControl();
     // rootNode.attachChild(geom);
    }
      private void initCam() {
        flyCam.setEnabled(true);
        cam.setLocation(new Vector3f(3f, 6f, -15f));
        cam.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);
    }
    
    private void initLight(){
    
          DirectionalLight sun = new DirectionalLight();
          sun.setDirection(new Vector3f(-1,-1,-1).normalizeLocal());
          rootNode.addLight(sun);
    
    }

     public void   keyControl(){
        inputManager.addMapping("Lefts", new KeyTrigger(KeyInput.KEY_H));
        inputManager.addMapping("Rights", new KeyTrigger(KeyInput.KEY_K));
        inputManager.addMapping("Ups", new KeyTrigger(KeyInput.KEY_U));
        inputManager.addMapping("Downs", new KeyTrigger(KeyInput.KEY_J));
        inputManager.addMapping("Space", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addMapping("Reset", new KeyTrigger(KeyInput.KEY_R));
        inputManager.addListener(tank, "Lefts");
        inputManager.addListener(tank, "Rights");
        inputManager.addListener(tank, "Ups");
        inputManager.addListener(tank, "Downs");
        inputManager.addListener(tank, "Space");
        inputManager.addListener(tank, "Reset");
    
 
     }
     
      private void initPhysics() {
        // initialize the physics engine
        bullet = new BulletAppState();
        stateManager.attach(bullet);
        //bullet.setDebugEnabled(true);

        // add the ground
        RigidBodyControl phyWorld = new RigidBodyControl(0.0f); // static: mass = 0
        world.geomGround.addControl(phyWorld);
        bullet.getPhysicsSpace().add(phyWorld);

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
