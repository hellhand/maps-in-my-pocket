package org.mimp.dom.kml;

public class IconStyleType {

    IconType icon;
    
    public IconStyleType() {

    }
    
    public IconStyleType(IconType icon) {
        this.icon = icon;
    }

    public IconType getIcon() {
        return icon;
    }

    public void setIcon(IconType icon) {
        this.icon = icon;
    }
}