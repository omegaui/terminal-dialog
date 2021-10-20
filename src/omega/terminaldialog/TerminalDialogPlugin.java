package omega.terminaldialog;
import omega.utils.IconManager;

import omega.Screen;

import omega.plugin.Plugin;
import omega.plugin.PluginCategory;

import java.net.URL;

import omega.popup.OPopupItem;
public class TerminalDialogPlugin implements Plugin{

	private TerminalDialog terminalDialog;
	private OPopupItem item;
	
	@Override
	public boolean enable() {
		Screen.getScreen().getToolMenu().toolsPopup.addItem(item);
		return true;
	}
	
	@Override
	public boolean disable() {
		Screen.getScreen().getToolMenu().toolsPopup.removeItem("Terminal Window");
		return true;
	}
	
	@Override
	public boolean init() {
		terminalDialog = new TerminalDialog(Screen.getScreen());
		item = new OPopupItem(Screen.getScreen().getToolMenu().toolsPopup, "Terminal Window", IconManager.fluentconsoleImage, ()->terminalDialog.setVisible(true));
		return true;
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
		return "v2.0";
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
