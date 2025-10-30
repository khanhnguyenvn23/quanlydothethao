package GUI.GUITool;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import java.awt.*;

public class BorderTool {
    public static void setPadding(JComponent comp, int top, int left, int bottom, int right) {
        comp.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
    }

    public static void setBorderWithPadding(JComponent comp, Color color, int pad) {
        comp.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color),
            BorderFactory.createEmptyBorder(pad, pad, pad, pad)
        ));
    }

}

