package IronPackage;

import java.awt.Color;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.event.KeyEvent;

import javax.sound.sampled.Clip;

import net.aionstudios.n2d.DisplayManager;
import net.aionstudios.n2d.audio.AudioManager;
import net.aionstudios.n2d.bounds.BoundingBox;
import net.aionstudios.n2d.drawing.Sprite;
import net.aionstudios.n2d.entity.ClickableEntity;
/*import net.aionstudios.n2d.entity.ClickableEntity;*/
import net.aionstudios.n2d.entity.Entity;
import net.aionstudios.n2d.game.NightfallGame;
import net.aionstudios.n2d.movement.Vector2f;

public class Game1 extends NightfallGame {

	private Entity sprite;
	private ClickableEntity ce;
	boolean wP = false;
	boolean aP = false;
	boolean sP = false;
	boolean dP = false;
	
	private Sprite idleSprite = new Sprite("res/StickBotIdle.png");
	private long startAnimation;
	
	private Sprite walk1 = new Sprite("res/StickWalk1.png");
	private Sprite walk2 = new Sprite("res/StickWalk2.png");
	private Sprite walk3 = new Sprite("res/StickWalk3.png");
	private Sprite walk4 = new Sprite("res/StickWalk4.png");
	private Sprite walk5 = new Sprite("res/StickWalk5.png");
	private Sprite revwalk1 = new Sprite("res/StickRev1.png");
	private Sprite revwalk2 = new Sprite("res/StickRev2.png");
	private Sprite revwalk3 = new Sprite("res/StickRev3.png");
	private Sprite revwalk4 = new Sprite("res/StickRev4.png");
	private Sprite revwalk5 = new Sprite("res/StickRev5.png");
	private Sprite crouch = new Sprite("res/StickCrouch.png");
	
	public Game1(String name, int pixelSize, int width, int height) {
		super(name, pixelSize, width, height);
		// TODO Auto-generated constructor stub
		//Clip clip = AudioManager.loadSound();
		//AudioManager.playSound(clip);
		//AudioManager.stopSound(clip);
	}

	@Override
	public void initialize(DisplayManager dm) {
		sprite = new Entity(new Sprite("IdleSprites", "res/StickBotIdle.png", 10),
				new Vector2f(0,100), new BoundingBox(22, 22));
				KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

		            @Override
		            public boolean dispatchKeyEvent(KeyEvent ke) {
		                synchronized (Game1.class) {
		                    switch (ke.getID()) {
		                    case KeyEvent.KEY_PRESSED:
		                        if (ke.getKeyCode() == KeyEvent.VK_W) {
		                            wP = true;
		                        
		                        }
		                        if (ke.getKeyCode() == KeyEvent.VK_A) {
		                            aP = true;
		                        }
		                        if (ke.getKeyCode() == KeyEvent.VK_S) {
		                            sP = true;
		                        }
		                        if (ke.getKeyCode() == KeyEvent.VK_D) {
		                            dP = true;
		                        }
		                        break;
		                        
		                    case KeyEvent.KEY_RELEASED:
		                        if (ke.getKeyCode() == KeyEvent.VK_W) {
		                            wP = false;
		                            
		                        }
		                        if (ke.getKeyCode() == KeyEvent.VK_A) {
		                            aP = false;
		                        }
		                        if (ke.getKeyCode() == KeyEvent.VK_S) {
		                            sP = false;
		                        }
		                        if (ke.getKeyCode() == KeyEvent.VK_D) {
		                            dP = false;
		                        }
		                        break;
		                    }
		                    return false;
		                }
		            }
		        });
				ce = new ClickableEntity(new Sprite("res/squared.png"), new Sprite("res/squared.png"), new Sprite("res/squared-w.png"), new Vector2f(5,5), new BoundingBox(183, 183)) {

					@Override
					public void clicked() {
						System.out.println("clicked");
					}

					@Override
					public void hovered() {
						System.out.println("hovered");
					}
				
				};
	}

	@Override
	public void process(DisplayManager dm) {
		dm.getDrawer().setBackground(Color.LIGHT_GRAY);
		if(!wP&&!sP&&!aP&&!dP) {
			sprite.setSprite(idleSprite);
			startAnimation=-1;
		} else {
			if(startAnimation==-1) {
				startAnimation = this.getNightfall().gameTimeMillis()%1000;
			}
		}
		if(dP) {
			long position = (this.getNightfall().gameTimeMillis()-startAnimation)%1000;
			if(position<200) {
				sprite.setSprite(walk1);
		
			}
			else if(position<400) {
				sprite.setSprite(walk2);
			}
			else if(position<600) {
				sprite.setSprite(walk3);
			}
			else if(position<800) {
				sprite.setSprite(walk4);
			}
			else if(position<1000) {
				sprite.setSprite(walk5);
			}
			sprite.getPosition().addX(40f*getNightfall().frameTimeMillis()/1000);
		} else if(aP) {
			long position = (this.getNightfall().gameTimeMillis()-startAnimation)%1000;
			sprite.getPosition().addX(-40f*getNightfall().frameTimeMillis()/1000);
			if(position<200) {
				sprite.setSprite(revwalk1);
			}
			else if (position<400) {
				sprite.setSprite(revwalk2);
			}
			else if (position<600) {
				sprite.setSprite(revwalk3);
			}
			else if (position<800) {
				sprite.setSprite(revwalk4);
			}
			else if (position<1000) {
				sprite.setSprite(revwalk5);
			}

		}else if (sP) {
			sprite.setSprite(crouch);
		}
		sprite.render(dm, new Point(0,0), new Point(sprite.getSprite().getWidth(), sprite.getSprite().getHeight()), false);
//		ce.render(dm, false);
	}

	@Override
	public void terminate(DisplayManager dm) {
		
	}
}
