package state;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public abstract class GamePanel extends JPanel {
    protected Font font;
    protected final ImageIcon bgImage;
    protected final Dimension screenSize;
    protected final float scale;

    public GamePanel(){
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        InputStream fileStream = getClass().getResourceAsStream("/Font/blrrpix.strict.ttf");

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, fileStream);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        this.setPreferredSize(screenSize);
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.setLayout(null);

        scale = this.getPreferredSize().width / 1000.0f;
        bgImage = new ImageIcon(getClass().getResource("/Background/Background.png"));
    }
}
