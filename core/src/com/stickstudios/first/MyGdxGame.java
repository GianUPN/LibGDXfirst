package com.stickstudios.first;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
	private int i = 0;
	private int estado = 1;
	private ShapeRenderer shapeRenderer;
	private OrthographicCamera camera;
	private Viewport viewport;
	private BitmapFont font;
	int cantidad_rezises=0;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("hongo/hongo.png");
		shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera();
		viewport = new FillViewport(600,450,camera);
		//viewport = new FitViewport(600,450,camera);
		//viewport = new FillViewport(450,600,camera);
		viewport.setScreenBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		font = new BitmapFont();
		font.getData().setScale(3);
		//font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
	}

	@Override
	public void resize(int width, int height){
		camera.viewportHeight=450;
		camera.viewportWidth = 600;
		camera.position.set(0,0,0);
		viewport.update(width, height, true);
		cantidad_rezises++;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		float delta = Gdx.graphics.getDeltaTime();
		//TODO: CAMARA
		viewport.apply();
		camera.update();
		shapeRenderer.setProjectionMatrix(camera.combined);
		batch.setProjectionMatrix(camera.combined);
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			camera.translate(180 * delta, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			camera.translate(-180 * delta, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			camera.translate(0, 180 * delta);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			camera.translate(0, -180 * delta);
		}

		//TODO: VIDEOJUEGO
		batch.begin();
		font.draw(batch,cantidad_rezises+"",100,40);
		batch.draw(img, i,0);
		//Izquierda a derecha Hongo
		if(estado==1) i++;
		else
			i--;
		if(i==-1){
			estado=1;
		}else if(i==100){
			estado=0;
		}
		batch.end();
		//PUNTITO
		shapeRenderer.begin(ShapeRenderer.ShapeType.Point);
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.point(100,100,0);
		shapeRenderer.end();
		//LINEAS
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.line(0,0,100,0);
		shapeRenderer.line(0,0,1,100);
		shapeRenderer.line(0,100,100,100);
		shapeRenderer.line(100,100,100,0);
		shapeRenderer.setColor(Color.GOLDENROD);
		shapeRenderer.end();
		//FILLED
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.rect(110,110,100,100,Color.BLUE,Color.MAGENTA,Color.RED,Color.GREEN);
		shapeRenderer.end();
		//CIRCULOS
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(Color.BLUE);
		int radius;
		for(radius=80;radius>0;radius-=10){
			shapeRenderer.circle(100,300,radius);
		}
		shapeRenderer.end();


	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		shapeRenderer.dispose();
		font.dispose();
	}
}
