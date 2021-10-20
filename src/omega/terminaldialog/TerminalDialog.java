package omega.terminaldialog;
import omega.token.factory.ShellTokenMaker;

import omega.terminal.Terminal;

import omega.Screen;

import omega.comp.FlexPanel;
import omega.comp.TextComp;

import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;
import javax.swing.JDialog;

import static omega.utils.UIManager.*;
import static omega.comp.Animations.*;
public class TerminalDialog extends JDialog{
	private Terminal terminal;
	public TerminalDialog(Screen screen){
		super(screen, false);
		setTitle("Terminal Dialog");
		setUndecorated(true);
		setSize(600, 400);
		setLocationRelativeTo(null);
		setLayout(null);
		setShape(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
		FlexPanel panel = new FlexPanel(null, back2, null);
		panel.setArc(20, 20);
		setContentPane(panel);
		setBackground(back2);
		init();
	}

	public void init(){
		TextComp titleComp = new TextComp("Terminal Window", TOOLMENU_COLOR3, c2, c2, null);
		titleComp.setBounds(0, 0, getWidth() - 30, 30);
		titleComp.setFont(PX14);
		titleComp.setArc(0, 0);
		titleComp.setClickable(false);
		titleComp.attachDragger(this);
		add(titleComp);

		TextComp closeComp = new TextComp("x", TOOLMENU_COLOR2_SHADE, back2, TOOLMENU_COLOR2, this::dispose);
		closeComp.setBounds(getWidth() - 30, 0, 30, 30);
		closeComp.setFont(PX14);
		closeComp.setArc(0, 0);
		add(closeComp);

		terminal = new Terminal();
		terminal.setBounds(2, 32, getWidth() - 4, getHeight() - 34);
          ShellTokenMaker.apply(terminal.getOutputArea());
		add(terminal);
	}

	@Override
	public void setVisible(boolean value){
		if(value){
	          terminal.launchTerminal();
		}
	     super.setVisible(value);
	}

	@Override
	public void dispose(){
		terminal.exit();
		super.dispose();
	}
}
