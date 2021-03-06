package com.space.engine.objects;

import com.space.engine.graphics.Mesh;
import com.space.engine.maths.Vector3f;
import com.space.util.Constants;

public class GameObject {
	private Vector3f position;
	private Vector3f rotation;
	private Vector3f scale;

	private boolean visible;

	private Constants.ObjecType objecType;

	private Mesh mesh;
	private Mesh meshAlternate; // TODO implement - Array of mesh instead of separate variables

	private boolean alternateMesh = false;

	public GameObject(Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.mesh = mesh;
		this.mesh.create();

		setVisible(true);
	}

	public GameObject(Vector3f position, Vector3f rotation, Vector3f scale) {
		this.rotation = rotation;
		this.scale = scale;
		this.position = position;

		setVisible(true);
	}

	public GameObject(Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh1, Mesh mesh2) {
		this.rotation = rotation;
		this.scale = scale;
		this.position = position;
		this.mesh = mesh1;
		this.meshAlternate = mesh2;
		this.mesh.create();
		this.meshAlternate.create();

		setVisible(true);
	}

	public void update() {
		position.setZ(position.getZ() - 0.05f);
	}

	public Vector3f getPosition() {
		return position;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public Vector3f getScale() {
		return scale;
	}

	public Mesh getMesh() {
		if(meshAlternate == null)
			return mesh;

		if(alternateMesh)
			return meshAlternate;
		else
			return mesh;

	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}

	public void setMesh(Mesh mesh) {
		this.mesh = mesh;
		this.mesh.create();
	}

	public void moveX(float x) {
		setPosition(new Vector3f(getPosition().getX() + x, getPosition().getY(), getPosition().getZ()));
	}

	public void moveY(float y) {
		setPosition(new Vector3f(getPosition().getX(), getPosition().getY() + y, getPosition().getZ()));
	}

	public void rotateY(float y) {
		setRotation(new Vector3f(getRotation().getX(), getRotation().getY(), getRotation().getZ() + y));
	}

	public void destroy() {
		mesh.destroy();

		if(meshAlternate != null)
			meshAlternate.destroy();
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Constants.ObjecType getObjecType() {
		return objecType;
	}

	public void setObjecType(Constants.ObjecType objecType) {
		this.objecType = objecType;
	}

	public boolean isAlternateMesh() {
		return alternateMesh;
	}

	public void setAlternateMesh(boolean alternateMesh) {
		this.alternateMesh = alternateMesh;
	}

}