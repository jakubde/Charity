// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

//Pie Chart Values
var thisJsScript = $('script[src*=chart-pie-demo]');
let pieChartValues = thisJsScript.attr('pieChartValues');
pieChartValues = pieChartValues.replace("[","");
pieChartValues = pieChartValues.replace("]","");
let pieChartValuesArray = pieChartValues.split(", ");

//Pie Chart Labels
var thisJsScript = $('script[src*=chart-pie-demo]');
let pieChartLabels = thisJsScript.attr('pieChartLabels');
pieChartLabels = pieChartLabels.replace("[","");
pieChartLabels = pieChartLabels.replace("]","");
let pieChartLabelsArray = pieChartLabels.split(", ");

console.log(pieChartLabels);
console.log(pieChartValues);
console.log(pieChartLabelsArray);
console.log(pieChartValuesArray);


// Pie Chart Example
var ctx = document.getElementById("myPieChart");
var myPieChart = new Chart(ctx, {
  type: 'doughnut',
  data: {
    labels: pieChartLabelsArray,
    datasets: [{
      data: pieChartValuesArray,
      backgroundColor: ['#4e73df', '#36b9cc', '#1cc88a', '#ea2f00', '#F2D300'],
      hoverBackgroundColor: ['#2e59d9', '#2c9faf', '#17a673', '#c32e00', '#C3A500'],
      hoverBorderColor: "rgba(234, 236, 244, 1)",
    }],
  },
  options: {
    maintainAspectRatio: false,
    tooltips: {
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      xPadding: 15,
      yPadding: 15,
      displayColors: false,
      caretPadding: 10,
    },
    legend: {
      display: false
    },
    cutoutPercentage: 80,
  },
});
