/*
 * Copyright (C) 2013 JPEXS
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jpexs.decompiler.flash.gui;

import com.jpexs.decompiler.flash.SWF;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

/**
 *
 * @author JPEXS
 */
public class SWFPreviwPanel extends JPanel {

    ImagePanel pan;
    Timer timer;
    int frame = 0;
    List<BufferedImage> frameImages = new ArrayList<BufferedImage>();
    JLabel buffering = new JLabel("Buffering...");

    public SWFPreviwPanel() {
        pan = new ImagePanel();
        setLayout(new BorderLayout());
        add(pan, BorderLayout.CENTER);
        add(buffering, BorderLayout.SOUTH);
        buffering.setHorizontalAlignment(JLabel.CENTER);
        buffering.setVisible(false);
        JLabel prevLabel = new JLabel("SWF preview (Internal viewer)");
        prevLabel.setHorizontalAlignment(SwingConstants.CENTER);
        prevLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
        add(prevLabel, BorderLayout.NORTH);
    }
    private SWF swf;

    public void load(final SWF swf) {
        this.swf = swf;
        new Thread() {
            @Override
            public void run() {
                buffering.setVisible(true);
                SWF.framesToImage(0, frameImages, 1, swf.frameCount, swf.tags, swf.tags, swf.displayRect, swf.frameCount);
                buffering.setVisible(false);
            }
        }.start();


    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void play() {
        if (swf == null) {
            return;
        }
        stop();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int newframe = (frame == swf.frameCount ? 1 : frame + 1);
                if (frameImages.size() >= newframe) {
                    pan.setImage(frameImages.get(newframe - 1));
                    frame = newframe;
                }
            }
        }, 0, 1000 / swf.frameRate);
    }
}