import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GradeTracker
{

    private JFrame frame;
    private JTextField gradeField;
    private JButton addButton, calculateButton;
    private JLabel averageLabel, highestLabel, lowestLabel, gradesListLabel;
    private ArrayList<Integer> grades;

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> 
        {
            try 
            {
                GradeTracker window = new GradeTracker();
                window.frame.setVisible(true);
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        });
    }

    public GradeTracker()
     {
        grades = new ArrayList<>();

        frame = new JFrame("Student Grade Tracker");
        frame.setBounds(100, 100, 400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        JLabel gradeLabel = new JLabel("Enter grade:");
        inputPanel.add(gradeLabel);

        gradeField = new JTextField(10);
        inputPanel.add(gradeField);

        addButton = new JButton("Add Grade");
        inputPanel.add(addButton);

        frame.getContentPane().add(inputPanel);

        
        addButton.addActionListener(e -> addGrade());

      
        calculateButton = new JButton("Calculate Statistics");
        frame.getContentPane().add(calculateButton);

        calculateButton.addActionListener(e -> calculateStatistics());


        averageLabel = new JLabel("Average Grade: N/A");
        highestLabel = new JLabel("Highest Grade: N/A");
        lowestLabel = new JLabel("Lowest Grade: N/A");

        frame.getContentPane().add(averageLabel);
        frame.getContentPane().add(highestLabel);
        frame.getContentPane().add(lowestLabel);

    
        gradesListLabel = new JLabel("Grades: ");
        frame.getContentPane().add(gradesListLabel);

        updateGradesList();

        frame.setLocationRelativeTo(null);
    }

    
    private void addGrade() 
    {
        try
         {
            int grade = Integer.parseInt(gradeField.getText());
            if (grade < 0 || grade > 100) 
            {
                JOptionPane.showMessageDialog(frame, "Please enter a grade between 0 and 100.", "Invalid Grade", JOptionPane.ERROR_MESSAGE);
                return;
            }
            grades.add(grade);
            gradeField.setText(""); 
            updateGradesList();
        } 
        catch (NumberFormatException e)
       {
            JOptionPane.showMessageDialog(frame, "Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void updateGradesList() 
    {
        StringBuilder sb = new StringBuilder("Grades: ");
        for (Integer grade : grades) 
        {
            sb.append(grade).append(" ");
        }
        gradesListLabel.setText(sb.toString());
    }

    
    private void calculateStatistics() 
    {
        if (grades.isEmpty())
         {
            JOptionPane.showMessageDialog(frame, "No grades entered yet.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int total = 0;
        int highest = grades.get(0);
        int lowest = grades.get(0);


        for (int grade : grades) 
        {
            total += grade;
            if (grade > highest)
             {
                highest = grade;
            }
            if (grade < lowest)
             {
                lowest = grade;
            }
        }

        double average = (double) total / grades.size();

        
        averageLabel.setText(String.format("Average Grade: %.2f", average));
        highestLabel.setText("Highest Grade: " + highest);
        lowestLabel.setText("Lowest Grade: " + lowest);
    }
}
