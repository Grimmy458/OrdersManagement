package com.OrdersManagement.utils.UI;

import java.util.ArrayList;

public class MenuView extends AbstractView {
    protected ArrayList<AbstractView> menuViews = new ArrayList<>();
    public MenuView(String displayTitle, String displayName) {
        super(displayTitle, displayName);
    }

    public void addMenuView(AbstractView menuView) {
        menuView.setParentView(this);
        menuViews.add(menuView);
    }


    @Override
    public void show() {
        this.println(displayTitle);
        for (int i = 0; i < menuViews.size(); i++) {
            this.println(i + 1 + " - " + menuViews.get(i).getDisplayName());
        }
        Boolean hasParent = parentView != null;
        if (hasParent) {
            this.println("0 - Back");
        } else {
            this.println("0 - Exit");
        }
        int selection = this.prompt("Select an option:", Integer.class);
        if (selection == 0) {
            if (hasParent) {
                this.back();
            } else {
                this.println("Bye!");
                System.exit(0);
            }
        } else if (selection > 0 && selection <= menuViews.size()) {
            menuViews.get(selection - 1).show();
        } else {
            this.println("Invalid selection");
            this.show();
        }

    }
}
