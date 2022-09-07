package View;



import CryproCoin.Coin;
import CryproCoin.Trader;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GraphView extends Pane{
	
	
	private NumberAxis xAxis;
	private NumberAxis yAxis;
	private LineChart<Number,Number> lineChart;
	@SuppressWarnings("rawtypes")
	private XYChart.Series series;
	
	@SuppressWarnings("rawtypes")
	public GraphView(int xPos , int yPos , Coin coin) {
		this.relocate(xPos, yPos);
		xAxis = new NumberAxis();
        yAxis = new NumberAxis();
        
       
        yAxis.setLabel("Price Change");
        
        
        lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        lineChart.setCreateSymbols(false);
        lineChart.setTitle("Coin Graph");
        lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");

        
        series = new XYChart.Series();
        
        xAxis.setForceZeroInRange(false);
        
        lineChart.getData().add(series);
        lineChart.setMaxHeight(220);
        lineChart.setMaxWidth(380);
        lineChart.setLegendVisible(false);
        
        
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(coin.getValue() - coin.getValue()*0.001);
        yAxis.setUpperBound(coin.getValue() + coin.getValue()*0.001);
        
        lineChart.getYAxis().tickLabelFillProperty().set(Color.AQUA);
        Node line = series.getNode().lookup(".chart-series-line");
        line.setStyle("-fx-stroke: yellow;");
        
        this.getChildren().add(lineChart);
	}
	
	public GraphView(int xPos , int yPos) {
		this.relocate(xPos, yPos);
		xAxis = new NumberAxis();
        yAxis = new NumberAxis();
        
       
        yAxis.setLabel("Price Change");
        
        
        lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        lineChart.setCreateSymbols(false);
        lineChart.setTitle("Coin Graph");
        lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");

        
        series = new XYChart.Series();
        
        xAxis.setForceZeroInRange(false);
        
        lineChart.getData().add(series);
        lineChart.setMaxHeight(220);
        lineChart.setMaxWidth(380);
        lineChart.setLegendVisible(false);
        
        
        yAxis.setAutoRanging(false);
        double balance = Trader.getWallet().getBalance();
        yAxis.setLowerBound(balance - balance*0.01);
        yAxis.setUpperBound(balance + balance*0.01);
        
        lineChart.getYAxis().tickLabelFillProperty().set(Color.AQUA);
        Node line = series.getNode().lookup(".chart-series-line");
        line.setStyle("-fx-stroke: yellow;");
        
        lineChart.getYAxis().tickLabelFillProperty().set(Color.AQUA);
        
        
        this.getChildren().add(lineChart);
	}
	

	@SuppressWarnings("unchecked")
	public void addData(double x, double y) {
		series.getData().add(new XYChart.Data(x, y));
	}
	
	public void removeOld() {
		series.getData().remove(0);
	}
	
	public NumberAxis getYAxis() {
		return yAxis;
	}
	
	/*
	 * By Uriel Malka www.GitHub.com/urielmalka
	 */
}
