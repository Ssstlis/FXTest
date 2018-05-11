
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainForm extends JFrame
{
    public MainForm(String title)
    {
        super(title != null ? title : "");
        setBounds(200,200,550,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Container pane = getContentPane();
        pane.setBackground(Color.decode("#e6de8a"));
        pane.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        final JTextField holder = new JTextField("");
        holder.setEditable(false);
        holder.setHorizontalAlignment(SwingConstants.CENTER);
        holder.setBorder(new EmptyBorder(0,0,0,0));
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.ipady = 40;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(5,5,5,5);
        constraints.gridx = 0;
        constraints.gridy = 0;
        pane.add(holder, constraints);

        constraints.ipady = 0;

        final JProgressBar bar0 = new JProgressBar(0, 1);
        bar0.setForeground(Color.decode("#91a2cc"));
        bar0.setStringPainted(true);
        bar0.setForeground(Color.decode("#91ccc7"));
        bar0.setUI(new BasicProgressBarUI()
        {
            @Override
            protected Color getSelectionBackground()
            {
                return Color.BLACK;
            }

            @Override
            protected Color getSelectionForeground()
            {
                return Color.BLACK;
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 1;
        pane.add(bar0, constraints);

        final JProgressBar bar1 = new JProgressBar(0, 1);
        bar1.setForeground(Color.decode("#91a2cc"));
        bar1.setStringPainted(true);
        bar1.setForeground(Color.decode("#91ccc7"));
        bar1.setUI(new BasicProgressBarUI()
        {
            @Override
            protected Color getSelectionBackground()
            {
                return Color.BLACK;
            }

            @Override
            protected Color getSelectionForeground()
            {
                return Color.BLACK;
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 2;
        pane.add(bar1, constraints);

        final JProgressBar bar2 = new JProgressBar(0, 1);
        bar2.setForeground(Color.decode("#91a2cc"));
        bar2.setStringPainted(true);
        bar2.setForeground(Color.decode("#91ccc7"));
        bar2.setUI(new BasicProgressBarUI()
        {
            @Override
            protected Color getSelectionBackground()
            {
                return Color.BLACK;
            }

            @Override
            protected Color getSelectionForeground()
            {
                return Color.BLACK;
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 3;
        pane.add(bar2, constraints);

        final JProgressBar bar3 = new JProgressBar(0, 1);
        bar3.setForeground(Color.decode("#91a2cc"));
        bar3.setStringPainted(true);
        bar3.setForeground(Color.decode("#91ccc7"));
        bar3.setUI(new BasicProgressBarUI()
        {
            @Override
            protected Color getSelectionBackground()
            {
                return Color.BLACK;
            }

            @Override
            protected Color getSelectionForeground()
            {
                return Color.BLACK;
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 4;
        pane.add(bar3, constraints);

        final JProgressBar bar4 = new JProgressBar(0, 100);
        bar4.setForeground(Color.decode("#91a2cc"));
        bar4.setStringPainted(true);
        bar4.setForeground(Color.decode("#91ccc7"));
        bar4.setUI(new BasicProgressBarUI()
        {
            @Override
            protected Color getSelectionBackground()
            {
                return Color.BLACK;
            }

            @Override
            protected Color getSelectionForeground()
            {
                return Color.BLACK;
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 5;
        pane.add(bar4, constraints);


        final JButton button = new JButton("Выберите папку..");
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (e.getSource() == button)
                {
                    JFileChooser chooser = new JFileChooser();
                    chooser.setDialogTitle("Выберите папку");
                    chooser.setApproveButtonText("Выбрать");
                    chooser.setPreferredSize(new Dimension(500, 640));
                    Action details = chooser.getActionMap().get("viewTypeDetails");
                    details.actionPerformed(null);
                    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    int Val = chooser.showOpenDialog(MainForm.this);

                    if (Val == JFileChooser.CANCEL_OPTION)
                        System.exit(2);

                    if (Val == JFileChooser.APPROVE_OPTION)
                    {
                        holder.setText(chooser.getSelectedFile().getPath());
                    }
                    else
                    {
                        button.doClick();
                    }
                }
            }
        });
        setVisible(true);
        setAlwaysOnTop(true);
        button.doClick();
        SwingWorker<Void, Void> swingWorker = new SwingWorker<Void, Void>()
        {
            @Override
            protected Void doInBackground() throws
                                            Exception
            {
                try
                {
                    testing.main(holder.getText(), bar0, bar1, bar2, bar3, bar4);
                    return null;
                }
                catch (Exception ignored)
                {
                }
                finally
                {
                    bar0.setValue(bar0.getMaximum());
                    bar1.setValue(bar1.getMaximum());
                    bar2.setValue(bar2.getMaximum());
                    bar3.setValue(bar3.getMaximum());
                    bar4.setValue(bar4.getMaximum());
                }
                return null;
            }
        };
        swingWorker.execute();
    }
}
