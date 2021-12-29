package omega.terminaldialog;
import omega.io.IconManager;

import omega.plugin.PluginCategory;
import omega.plugin.Plugin;

import omega.ui.popup.OPopupItem;

import omega.Screen;

import java.net.URL;
public class TerminalDialogPlugin implements Plugin{
	
	private OPopupItem integratedItem;
	private OPopupItem jettermItem;
	
	@Override
	public boolean enable() {
		Screen.getScreen().getToolMenu().toolsPopup.addItem(integratedItem);
		Screen.getScreen().getToolMenu().toolsPopup.addItem(jettermItem);
		return true;
	}
	
	@Override
	public boolean disable() {
		Screen.getScreen().getToolMenu().toolsPopup.removeItem("Integrated Terminal");
		Screen.getScreen().getToolMenu().toolsPopup.removeItem("Full-Fledge Terminal");
		return true;
	}
	
	@Override
	public boolean init() {
		integratedItem = new OPopupItem(Screen.getScreen().getToolMenu().toolsPopup, "Integrated Terminal", IconManager.fluentconsoleImage, ()->showIntegratedTerminal());
		jettermItem = new OPopupItem(Screen.getScreen().getToolMenu().toolsPopup, "Full-Fledge Terminal", IconManager.fluentconsoleImage, ()->showJetTerminal());
		return true;
	}

	public void showIntegratedTerminal(){
		new TerminalDialog(Screen.getScreen(), TerminalDialog.INTEGRATED_TERMINAL).setVisible(true);
	}

	public void showJetTerminal(){
		new TerminalDialog(Screen.getScreen(), TerminalDialog.JET_TERMINAL).setVisible(true);
	}
	
	@Override
	public URL getImage() {
		try{
			return getClass().getResource("/fluent-icons/icons8-console-50.png");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String getName() {
		return "Terminal Window";
	}
	
	@Override
	public String getVersion() {
		return "v2.2";
	}
	
	@Override
	public String getDescription() {
		return "The Integrated Terminal in a Window";
	}
	
	@Override
	public String getAuthor() {
		return "Omega UI";
	}
	
	@Override
	public String getLicense() {
		return "GNU GPL v3";
	}
	
	@Override
	public String getPluginCategory() {
		return PluginCategory.UTILITY;
	}
	
	@Override
	public String getSizeInMegaBytes() {
		return "0.20 MB";
	}
	
	@Override
	public boolean needsRestart() {
		return false;
	}
	
}
