package pl.dbjllmjk.View;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class PetListSelectionHandler implements ListSelectionListener {
	private UserMenu userMenu;

	public PetListSelectionHandler(UserMenu userMenu) {
		this.userMenu = userMenu;
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		this.userMenu.selectionMade();

	}

}