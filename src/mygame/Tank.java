/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.input.controls.AnalogListener;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;

/**
 *
 * @author 2
 */
public class Tank implements AnalogListener{
    
    Main main;
    Node tankNode;
      Quaternion quat;
        float yAngle=0; 
    
    public Tank(Main main){
        this.main = main;
        initNode();
        
        initBullet();
    }
    
    private void initNode(){
        /**barrel position (0,1,3), size(.1f, .1f, .1f)*/
     tankNode = (Node) main.getAssetManager().loadModel("Models/HoverTank/Tank2.mesh.xml");
          tankNode.setLocalScale(.5f);
//        DirectionalLight d1 = new DirectionalLight();
//          d1.setDirection(new Vector3f(1,1,1).normalizeLocal());
//          DirectionalLight d2 = new DirectionalLight();
//          d2.setDirection(new Vector3f(-1,-1,-1).normalizeLocal());
//          tankNode.addLight(d1);
//          tankNode.addLight(d2);
   //       tankNode.setLocalTranslation(0, 2, 0);
          tankNode.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
           main.getRootNode().attachChild(tankNode);
    }

    
    
    
    
    private void initBullet(){
            Sphere b = new Sphere(100,100, .2f);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(main.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);
      geom.setLocalTranslation(0, 2, 6);
          tankNode.attachChild(geom);
          
          
           Geometry geomFire = new Geometry("Box", b);
    geomFire.setMaterial(mat);
      geomFire.setLocalTranslation(1, 2, -6);
          tankNode.attachChild(geomFire);
    }
    
    
    public void onAnalog(String name, float value, float tpf) {
             quat = new Quaternion();
     if (name.equals("Left")) {
         yAngle +=FastMath.PI/128;
           quat.fromAngleAxis(yAngle,Vector3f.UNIT_Y);           
             tankNode.setLocalRotation(quat);   
            } else if (name.equals("Right")) {
                yAngle -=FastMath.PI/128;
                quat.fromAngleAxis(yAngle,Vector3f.UNIT_Y);     
             tankNode.setLocalRotation(quat);  
            } else if (name.equals("Ahead")) {  
               tankNode.move(0, 0, .01f); 
            } else if (name.equals("Back")) {                           
                tankNode.move(0, 0,-.01f); 
                }
               // barrelNode.move(0f,0f,tpf * 5);
            }
     
    }
    
    

