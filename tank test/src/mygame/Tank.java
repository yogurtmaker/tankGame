/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Matrix3f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Cylinder;
import com.jme3.scene.shape.Sphere;

/**
 *
 * @author 2
 */
public class Tank implements ActionListener{
    
    Main main;
    Node tankNode;
        float yAngle=0; 
        VehicleControl tankControl;
        Material mat;
        
     private final float accelerationForce = 1000.0f;
    private final float brakeForce = 100.0f;
    private float steeringValue = 0;
    private float accelerationValue = 0;
    private Vector3f jumpForce = new Vector3f(0, 3000, 0);
    
    public Tank(Main main){
        this.main = main;
        initNode();
        initBullet();
    }
    
    private void initNode(){
         mat = new Material(main.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
        mat.setBoolean("UseMaterialColors", true);
        mat.setColor("Ambient", ColorRGBA.Red);
        mat.setColor("Diffuse", ColorRGBA.Green);
        mat.setColor("Specular", ColorRGBA.Gray);
        mat.setFloat("Shininess", 4f);
        
        /**barrel position (0,1,3), size(.1f, .1f, .1f)*/
          Node tankBody = (Node) main.getAssetManager().loadModel("Models/HoverTank/Tank2.mesh.xml");
         tankBody.setLocalTranslation(0, 1f, 0);
         tankNode = new Node("playerTank");
         tankNode.attachChild(tankBody);
         tankNode.setLocalScale(.5f);
 
          tankNode.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
         

        CompoundCollisionShape compoundShape = new CompoundCollisionShape(); 
        BoxCollisionShape collChassis = new BoxCollisionShape(new Vector3f(1.2f, 0.5f, 2.4f));
        compoundShape.addChildShape(collChassis, new Vector3f(0, 1, 0));
        tankControl = new VehicleControl(compoundShape, 400);
        tankNode.addControl(tankControl);

        // Configure the VehicleControl
        // these values come from the demo website.
        float stiffness = 60.0f;//200=f1 car
        float compValue = .3f; //(should be lower than damp)
        float dampValue = .4f;
        tankControl.setSuspensionCompression(compValue * 2.0f * FastMath.sqrt(stiffness));
        tankControl.setSuspensionDamping(dampValue * 2.0f * FastMath.sqrt(stiffness));
        tankControl.setSuspensionStiffness(stiffness);
        tankControl.setMaxSuspensionForce(5000.0f);
        

        // add the wheels
        Vector3f wheelDirection = new Vector3f(0, -1, 0);
        Vector3f wheelAxle = new Vector3f(-1f, 0, 0);
        float radius = 1f;
        float restLength = 0.3f;
        float yOff = .5f;
        float xOff = 1.5f;
        float zOff = 2f;

        Cylinder wheelMesh = new Cylinder(160, 160, radius, radius * 0.6f, true);

        Node node1 = new Node("wheel 1 node");
        Geometry wheels1 = new Geometry("wheel 1", wheelMesh);
        node1.attachChild(wheels1);
        wheels1.rotate(0, FastMath.HALF_PI, 0);
        wheels1.setMaterial(mat);
      //  wheels1.setCullHint(Spatial.CullHint.Always);
        tankControl.addWheel(node1, new Vector3f(-xOff, yOff, zOff),
                wheelDirection, wheelAxle, restLength, radius, true);

        Node node2 = new Node("wheel 2 node");
        Geometry wheels2 = new Geometry("wheel 2", wheelMesh);
        node2.attachChild(wheels2);
        wheels2.rotate(0, FastMath.HALF_PI, 0);
        wheels2.setMaterial(mat);
       // wheels2.setCullHint(Spatial.CullHint.Always);
        tankControl.addWheel(node2, new Vector3f(xOff, yOff, zOff),
                wheelDirection, wheelAxle, restLength, radius, true);

        Node node3 = new Node("wheel 3 node");
        Geometry wheels3 = new Geometry("wheel 3", wheelMesh);
        node3.attachChild(wheels3);
        wheels3.rotate(0, FastMath.HALF_PI, 0);
        wheels3.setMaterial(mat);
        tankControl.addWheel(node3, new Vector3f(-xOff, yOff, -zOff),
                wheelDirection, wheelAxle, restLength, radius, false);

        Node node4 = new Node("wheel 4 node");
        Geometry wheels4 = new Geometry("wheel 4", wheelMesh);
        node4.attachChild(wheels4);
        wheels4.rotate(0, FastMath.HALF_PI, 0);
        wheels4.setMaterial(mat);
        tankControl.addWheel(node4, new Vector3f(xOff, yOff, -zOff),
                wheelDirection, wheelAxle, restLength, radius, false);

        // add everything to the scene graph
        tankNode.attachChild(node1);
        tankNode.attachChild(node2);
        tankNode.attachChild(node3);
        tankNode.attachChild(node4);
        main.getRootNode().attachChild(tankNode);
        // and add it to the physics engine
        main.bullet.getPhysicsSpace().add(tankControl);
    }
 
           


    
    
    
    
    private void initBullet(){
         Sphere b = new Sphere(100,100, .2f);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(main.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);
      geom.setLocalTranslation(0, 3, 6);
          tankNode.attachChild(geom);
                   
     Geometry geomFire = new Geometry("Box", b);
    geomFire.setMaterial(mat);
      geomFire.setLocalTranslation(.7f, 1.8f, -5.6f);
          tankNode.attachChild(geomFire);
    }
    
    
    

    public void onAction(String name, boolean down, float tpf) {
        if (name.equals("Lefts")) {
            if (down) {
                steeringValue += .5f;
            } else {
                steeringValue += -.5f;
            }
            tankControl.steer(steeringValue);
          
        } else if (name.equals("Rights")) {
            if (down) {
                steeringValue += -.5f;
            } else {
                steeringValue += .5f;
            }
            tankControl.steer(steeringValue);
        } else if (name.equals("Ups")) {
            if (down) {
                tankControl.brake(brakeForce);
                accelerationValue += accelerationForce;
            } else {
                accelerationValue -= accelerationForce;
            }
            tankControl.accelerate(accelerationValue);
        } else if (name.equals("Downs")) {
         if (down) {
                tankControl.brake(brakeForce);
                accelerationValue -= accelerationForce;
            } else {
                accelerationValue += accelerationForce;
            }
         tankControl.accelerate(accelerationValue);
        } else if (name.equals("Space")) {
            if (down) {
                tankControl.brake(brakeForce);
            } else {
                tankControl.brake(0f);
            }
        } else if (name.equals("Reset")) {
            if (down) {
                System.out.println("Reset");
               // tankControl.setPhysicsLocation(Vector3f.ZERO);
                tankControl.setPhysicsRotation(new Matrix3f());
                tankControl.setLinearVelocity(Vector3f.ZERO);
                tankControl.setAngularVelocity(Vector3f.ZERO);
                tankControl.resetSuspension();
            } 
        }
    
    
    }
}

   
    
    

