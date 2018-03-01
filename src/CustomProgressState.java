
import Fox.core.lib.general.templates.ProgressState;
import Fox.core.lib.general.utils.ProgressStateException;
import Fox.core.main.SearchLib;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;


public class CustomProgressState
        extends ProgressState
{
    private static Logger logger;
    private JProgressBar Bar;
    public CustomProgressState(
            JProgressBar bar,
            int size,
            String name,
            String desc)
            throws
            ProgressStateException
    {

        super(size,
              name,
              desc
             );
        Bar = bar;
        Bar.setMaximum(size);
        Bar.setString(name + " : " + desc);
        Bar.setStringPainted(true);
        Bar.setForeground(Color.cyan);
        Bar.setUI(new BasicProgressBarUI()
        {
            @Override
            protected Color getSelectionBackground() { return Color.WHITE; }

            @Override
            protected Color getSelectionForeground() { return Color.BLACK; }
        });
        logger = LoggerFactory.getLogger(SearchLib.class);
    }

    @Override
    public void onDone()
    {
        Bar.setForeground(Color.decode("#6BA863"));
        Bar.setString(name + " : DONE");
        if (logger.isInfoEnabled())
            logger.info("Progress bar {} is done", this.name);
    }

    @Override
    protected void onResize()
    {
        Bar.setMaximum(this.size);
        Bar.setValue(this.state);
        Bar.setString(name + " : " + this.state + " \\ " + this.size);
    }

    @Override
    public void onChange()
    {
        Bar.setValue(this.state);
        Bar.setString(name + " : " + this.state + " \\ " + this.size);
        if (logger.isInfoEnabled())
            logger.info("{} \\ {} \\ {}", this.state, this.size, this.desc);
    }

}
