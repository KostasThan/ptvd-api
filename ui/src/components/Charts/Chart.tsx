import React, { memo } from "react";
import { ChartType } from "../../types";
import { Bar, Line, Scatter } from "react-chartjs-2";

import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
  BarElement,
} from "chart.js";

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  BarElement,
  Title,
  Tooltip,
  Legend
);

const formatData = (data: any, aggregateBy: number) => {
  const fixedDataSets: any[] = [];
  if (data.labels.length !== data.datasets[0].data.length) {
    data.datasets.forEach((dataset: any) => {
      const newData = new Array(data.labels.length).fill(null);
      dataset.data.forEach((value: any) => {
        newData[data.labels.indexOf(value?.year.toString())] = value?.metric;
      });
      fixedDataSets.push({ ...dataset, data: newData });
    });
    return { labels: data.labels, datasets: fixedDataSets };
  } else {
    return {
      labels: data.labels,
      datasets: data.datasets.map((innerData: any) => {
        return {
          ...innerData,
          data: innerData.data.map((d: any) => (d?.metric ? d.metric : null)),
        };
      }),
    };
  }
};

export const Chart: React.FunctionComponent<ChartProps> = (props) => {
  const { data, type, aggregadedByYears, scatterExtraInfo, aggregateBy } =
    props;

  const labelLine = (tooltipItem: any) => {
    return `${tooltipItem[0].label}  ${
      aggregadedByYears &&
      aggregadedByYears > 1 &&
      `to ${+tooltipItem[0].label + aggregadedByYears - 1}`
    }`;
  };

  const renderChart = () => {
    const chartOptions = {
      plugins: {
        responsive: true,
        tooltip: {
          callbacks: {
            title: labelLine,
          },
        },
        title: {
          display: true,
          fullSize: true,
          text: aggregadedByYears
            ? `Aggregated results by ${aggregadedByYears} years`
            : "PLOT",
          font: {
            size: 18,
          },
          padding: {
            top: 5,
            bottom: 5,
          },
        },
        legend: {
          display: true,

          labels: {
            color: "rgb(55, 55, 55)",
          },
        },
      },
    };

    switch (type) {
      case ChartType.Bar:
        return (
          <Bar data={formatData(data, aggregateBy)} options={chartOptions} />
        );
      case ChartType.Line:
        return (
          <Line data={formatData(data, aggregateBy)} options={chartOptions} />
        );
      case ChartType.Scatter:
        const formatScatterLabel = (tooltipItem: any) => {
          return "Year:" + data.labels[tooltipItem.dataIndex];
        };
        const afterLabel = (tooltipItem: any) => {
          return `${scatterExtraInfo?.ylabel}:${tooltipItem.raw.y}\n${scatterExtraInfo?.xlabel}:${tooltipItem.raw?.x}`;
        };

        const formattedScatterData = 
           data.datasets[0].data.map(
            (innerData: {
              x: { year: string; metric: number };
              y: { year: string; metric: number };
            }) => {
              return { x: innerData?.x?.metric, y: innerData?.y?.metric };
            }
          )
        ;

        const scatterData = {
          labels: data.labels,
          datasets: [
            {
              label: data.datasets[0].label,
              data: formattedScatterData,
              backgroundColor: data.datasets[0].label,
            },
          ],
        };

        const scatterPlotOptions = {
          responsive: true,
          plugins: {
            legend: { display: false },
            subtitle: {
              display: true,
              text: aggregadedByYears
                ? `Aggregated results by ${aggregadedByYears} years`
                : "PLOT",
              font: { size: 14 },
            },
            title: {
              display: true,
              text: `Correlation between ${scatterExtraInfo?.xlabel} and ${scatterExtraInfo?.ylabel}`,
              font: {
                size: 18,
              },
            },
            tooltip: {
              callbacks: {
                label: formatScatterLabel,
                afterLabel: afterLabel,
              },
            },
          },
          scales: {
            y: {
              title: { text: scatterExtraInfo?.ylabel, display: true },
            },
            x: {
              title: { text: scatterExtraInfo?.xlabel, display: true },
            },
          },
        };
        console.log("Scatter data", scatterData);
        
        return <Scatter data={scatterData} options={scatterPlotOptions} />;
      default:
        break;
    }
  };

  return <div className="d-flex w-100 align-self-center">{renderChart()}</div>;
};

interface ChartProps {
  aggregateBy: number;
  type: ChartType;
  data: any;
  scatterExtraInfo?: { xlabel: string; ylabel: string };
  aggregadedByYears?: number;
}

export default memo(Chart);
