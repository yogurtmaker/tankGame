/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;

/**
 *
 * @author 2
 */
public class World {
    
    Main main;
    Geometry geomGround;
    Node worldNode;
    
    public World(Main main){
        this.main = main;
        initWorld();
    }
    
    private void initWorld(){
        worldNode = new Node();
        Material matGround = new Material(main.getAssetManager(),  "Common/MatDefs/Misc/Unshaded.j3md");
        matGround.setTexture("ColorMap", main.getAssetManager().loadTexture("Textures/desert.jpg"));
           
        
       Box box = new Box(100, 0.1f,100);
        geomGround = new Geometry("ground", box);
        geomGround.setMaterial(matGround);
       //  geomGround.setLocalTranslation(0, -2, 1);
       geomGround.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
        
        worldNode.attachChild(geomGround);
        main.getRootNode().attachChild(worldNode);
    }
    
    
                                                                        
}
