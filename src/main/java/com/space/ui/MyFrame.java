package com.space.ui;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;

public class MyFrame {

    public String title = "The SuperMatrix";
    public GraphicsDevice gd;
    private long display;

    private GLFWErrorCallback glfwerrorcallback;

    public MyFrame() {

        this.gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        this.glfwerrorcallback = GLFWErrorCallback.createPrint(System.err);
        this.glfwerrorcallback.set();
        this.start();
    }

    public void start() {

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        this.display = glfwCreateWindow(800, 600, this.title, 0, 0);


        System.out.println(this.display);

        try (MemoryStack stack = stackPush()) {

            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(this.display, pWidth, pHeight);
            GLFWVidMode mode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwSetWindowPos(this.display,
                    (mode.width()-pWidth.get(0))/2,
                    (mode.height()-pHeight.get(0))/2
            );

        } catch (Exception e) {
           System.err.println(e);
        }

        glfwMakeContextCurrent(this.display);
        glfwSwapInterval(1);
        glfwShowWindow(this.display);

        this.loop();

    }

    public void loop() {

        GL.createCapabilities();

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,800, 0, 600, -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glDisable(GL_DEPTH_TEST);

        while(!glfwWindowShouldClose(this.display)) {

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glfwPollEvents();
            glPushMatrix();

            glBegin(GL_QUADS);

            glColor3f(1,0,1);
            glVertex2f(0, 0);
            glVertex2f(0, 64);
            glVertex2f(64, 64);
            glVertex2f(64, 0);

            glEnd();

            glPopMatrix();
            glfwSwapBuffers(this.display);

        }

        this.destroy();

    }

    public void destroy() {

        glfwFreeCallbacks(this.display);
        glfwDestroyWindow(this.display);

        glfwTerminate();
        this.glfwerrorcallback.free();

    }

}