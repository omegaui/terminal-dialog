package omega.terminaldialog;
import omega.ui.component.jediterm.JetTerminal;

import omega.ui.component.Terminal;

import omega.io.ShellTokenMaker;
import omega.io.IconManager;

import omegaui.component.FlexPanel;
import omegaui.component.TextComp;

import omega.Screen;

import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;
import javax.swing.JDialog;

import static omega.io.UIManager.*;
import static omegaui.component.animation.Animations.*;
public class TerminalDialog extends JDialog{

	public static final int INTEGRATED_TERMINAL = 0;
	public static final int JET_TERMINAL = 1;
	private int type;

	private Runnable disposeAction = ()->{};
	
	public TerminalDialog(Screen screen, int type){
		super(screen, false);
		this.type = type;
		setTitle("Terminal Dialog");
		setUndecorated(true);
		setSize(600, 400);
		setLocationRelativeTo(null);
		setLayout(null);
		setShape(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
		FlexPanel panel = new FlexPanel(null, back1, null);
		panel.setArc(20, 20);
		setContentPane(panel);
		setBackground(back2);
		init();
	}
	
	public void init(){
		TextComp titleComp = new TextComp("Terminal", c2, c2, glow, null);
		titleComp.setBounds(0, 0, getWidth() - 25, 25);
		titleComp.setFont(PX14);
		titleComp.setArc(0, 0);
		titleComp.setClickable(false);
		titleComp.attachDragger(this);
		add(titleComp);
		
		TextComp closeComp = new TextComp(IconManager.closeImage, 20, 20, TOOLMENU_COLOR2_SHADE, c2, TOOLMENU_COLOR2, this::dispose);
		closeComp.setBounds(getWidth() - 25, 0, 25, 25);
		closeComp.setArc(0, 0);
		add(closeComp);

		putAnimationLayer(closeComp, getImageSizeAnimationLayer(20, 5, true), ACTION_MOUSE_ENTERED);
	}
	
	@Override
	public void setVisible(boolean value){
		if(value){
			if(type == INTEGRATED_TERMINAL){
				Terminal terminal = new Terminal();
				terminal.setBounds(2, 32, getWidth() - 4, getHeight() - 34);
				ShellTokenMaker.apply(terminal.getOutputArea());
				terminal.launchTerminal();
				add(terminal);
				
				disposeAction = terminal::exit;
			}
			else{
				JetTerminal terminal = new JetTerminal();
				terminal.setOnProcessExited(()->{
					dispose();
				});
				terminal.setBounds(2, 32, getWidth() - 4, getHeight() - 34);
				terminal.start();
				add(terminal);

				disposeAction = terminal::exit;
			}
			
		}
		super.setVisible(value);
	}
	
	@Override
	public void dispose(){
		disposeAction.run();
		super.dispose();
	}
}
