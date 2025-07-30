package views.Admin;

import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;

public class AnimatedTextField extends JTextField {
    private float underlineAlpha = 0f;
    private Timer timer;
    private boolean gainingFocus = false;
    private final Color underlineColor = new Color(70, 130, 180);
    public AnimatedTextField() {
        setBorder(null);
        setOpaque(false);
        setForeground(Color.BLACK);
        setFont(new Font("Arial", Font.PLAIN, 16));
        setCaretColor(new Color(70, 130, 180));
        addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                gainingFocus = true;
                animateUnderline();
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                gainingFocus = false;
                animateUnderline();
            }
        });
    }
    private void animateUnderline() {
        if (timer != null && timer.isRunning()) timer.stop();
        timer = new Timer(15, null);
        timer.addActionListener(_ -> {
            if (gainingFocus) {
                underlineAlpha += 0.08f;
                if (underlineAlpha >= 1f) {
                    underlineAlpha = 1f;
                    timer.stop();
                }
            } else {
                underlineAlpha -= 0.08f;
                if (underlineAlpha <= 0f) {
                    underlineAlpha = 0f;
                    timer.stop();
                }
            }
            repaint();
        });
        timer.start();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int y = getHeight() - 2;
        g2.setColor(new Color(underlineColor.getRed(), underlineColor.getGreen(), underlineColor.getBlue(), (int)(underlineAlpha * 255)));
        g2.fillRoundRect(0, y, getWidth(), 3, 3, 3);
        g2.dispose();
    }
} 