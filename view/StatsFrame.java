package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import Model.DB;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.*;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import controller.Messages;

public class StatsFrame extends JPanel{

    private AppFrame frame;

    public StatsFrame() {

        frame= new AppFrame("Consulter les Statiqtiques",300,190,false);
        frame.getContentPane().setLayout(null);

        JButton evoBtn = new JButton("Evolution du nombre d'absences");
        evoBtn.setFont(new Font("Titillium Web SemiBold", Font.PLAIN, 12));
        evoBtn.setBounds(30, 35, 230, 44);
        frame.getContentPane().add(evoBtn);
        evoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new AbsChart();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton disBtn = new JButton("Distribution des absences par classes");
        disBtn.setFont(new Font("Titillium Web SemiBold", Font.PLAIN, 12));
        disBtn.setBounds(30, 90, 230, 44);
        frame.getContentPane().add(disBtn);

        disBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new AbsGraph();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
}

class AbsChart {
    private AppFrame frame;
    private ChartPanel panel;
    public AbsChart() throws SQLException {
        frame= new AppFrame("stats",700,560,false);
        frame.setSize(700,570);
        DefaultPieDataset dataset =  createData();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        frame.setContentPane(chartPanel);


    }

    private DefaultPieDataset createData() throws SQLException {
        DefaultPieDataset dataset = new DefaultPieDataset();
        ResultSet rs = DB.get("SELECT count(*) , date_abs FROM projetjava.absence group by date_abs order by  date_abs desc limit 10; ");
        while(rs.next()){
            int count = rs.getInt("count(*)");
            String date = rs.getString("date_abs");
            dataset.setValue(date , count);
        }
        return dataset;
    }
    private JFreeChart createChart(DefaultPieDataset dataset) {

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Absence par jours",
                dataset,
                false, true, false);

        return pieChart;
    }
}

class AbsGraph {
    private AppFrame frame;
    private ChartPanel panel;
    public AbsGraph() throws SQLException {
        frame= new AppFrame("stats",700,560,false);
        frame.setSize(700,570);
        DefaultCategoryDataset dataset =  createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        frame.setContentPane(chartPanel);
    }
    private DefaultCategoryDataset createDataset() throws SQLException {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ResultSet rs = DB.get("SELECT count(*) , date_abs FROM projetjava.absence group by date_abs order by  date_abs desc limit 10; ");
        while(rs.next()){
            int count = rs.getInt("count(*)");
            String date = rs.getString("date_abs");
            dataset.setValue(count ,"absence",date);
        }

        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {

        JFreeChart barChart = ChartFactory.createBarChart(
                "Absences",
                "",
                "Gold medals",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        return barChart;
    }

}