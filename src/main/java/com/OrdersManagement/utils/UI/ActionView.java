package com.OrdersManagement.utils.UI;

public abstract class ActionView  extends AbstractView {
    public ActionView(String displayTitle, String displayName) {
        super(displayTitle, displayName);
    }
    public abstract void executeAction();
    @Override
    public void show() {
        this.println("");
        this.println(this.displayTitle);
        this.executeAction();
        this.back();
    }
}
