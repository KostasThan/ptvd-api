import React, { memo, useCallback, useEffect, useState } from "react";
import { ChartDataInterval, ChartType } from "../../types";
import { Chart } from "../Charts/Chart";
import styles from "./MainContent.module.scss";
import { Toast } from "react-bootstrap";
import yoda from "../../assets/GOby.gif";
import { PlotDataSelectionList } from "./PlotDataSelectionList";
import { ColorUtils } from "../../utils";
import { Drawer } from "@mui/material";

const getLabels = (rangeOfYears: number[], aggregateBy: number) => {
  const YaxisLabels = [];
  const step = Boolean(aggregateBy) ? aggregateBy : 1;
  for (let index = rangeOfYears[0]; index < rangeOfYears[1]; index += step) {
    YaxisLabels.push(index.toString());
  }
  return YaxisLabels;
};

export const MainContent: React.FunctionComponent<MainContentProps> = (
  props
) => {
  const { chartType, chartFamily, rangeOfYears, aggregateBy } = props;

  const [showSidebar, setShowSidebar] = useState(false);

  const [selectedData, setData] = useState<ChartDataInterval[]>([]);
  const [show, setShow] = useState({ show: false, message: "" });

  const [selectedDataX, setDataX] = useState<ChartDataInterval[]>([]);
  const [selectedDataY, setDataY] = useState<ChartDataInterval[]>([]);

  const [yAxisLabels, setYaxisLabels] = useState<string[]>(
    getLabels(rangeOfYears, aggregateBy)
  );

  const plotData = {
    labels: yAxisLabels,
    datasets: selectedData,
  };

  const handleSidebarOpen = useCallback(() => {
    setShowSidebar(true);
  }, []);

  const handleSidebarClose = useCallback(() => {
    setShowSidebar(false);
  }, []);

  useEffect(() => {
    setYaxisLabels(getLabels(rangeOfYears, aggregateBy));
  }, [rangeOfYears, aggregateBy]);

  useEffect(() => {
    if (chartType !== "Select Type" && !selectedData.length) {
      setShowSidebar(true);
    }
  }, [chartFamily, chartType, selectedData]);

  useEffect(() => {
    setData([]);
    setDataX([]);
    setDataY([]);
  }, [chartFamily]);

  const formatScatterData = () => {
    const fdata = { data: [{}] };
    if (selectedDataX[0]?.data && selectedDataY[0]?.data) {
      for (let index = 0; index < selectedDataX[0].data.length; index++) {
        const elementx = selectedDataX[0].data[index];
        const elementy = selectedDataY[0].data[index];
        if (index === 0) {
          fdata.data.pop();
        }
        fdata.data.push({ x: elementx, y: elementy });
      }
    }
    return {
      labels: yAxisLabels,
      datasets: [
        {
          label: "Correlations",
          data: fdata.data,
          backgroundColor: ColorUtils.getrandomColor(),
        },
      ],
    };
  };

  const renderToastMessage = () => (
    <Toast
      className="position-absolute top-0 start-50 bg-warning"
      onClose={() => setShow({ show: false, message: "" })}
      show={show.show}
      delay={3000}
      autohide
    >
      <Toast.Header>
        <img src="holder.js/20x20?text=%20" className="rounded me-2" alt="" />
        <strong className="me-auto">Warning!</strong>
      </Toast.Header>
      <Toast.Body>{show.message}</Toast.Body>
    </Toast>
  );

  const renderScatterDataListItems = () => (
    <>
      <span className="fw-bold me-auto ms-2">Y-Axis: </span>
      <PlotDataSelectionList
        chartType={chartType}
        yearsRange={rangeOfYears}
        aggregateByYears={aggregateBy}
        selectedData={selectedDataY}
        setData={(newData) => setDataY(newData)}
        triggerPopUp={(message) => setShow(message)}
        isCorrelationPlot={false}
      />
      <span className="fw-bold me-auto ms-2">X-Axis: </span>
      <PlotDataSelectionList
        chartType={chartType}
        yearsRange={rangeOfYears}
        aggregateByYears={aggregateBy}
        selectedData={selectedDataX}
        setData={(newData) => setDataX(newData)}
        triggerPopUp={(message) => setShow(message)}
        isCorrelationPlot={false}
      />
    </>
  );

  const renderYodaOrScatter = () => {
    return selectedDataX.length && selectedDataY.length ? (
      <Chart
        aggregateBy={aggregateBy}
        data={formatScatterData()}
        type={ChartType.Scatter}
        scatterExtraInfo={{
          xlabel: selectedDataX[0].label,
          ylabel: selectedDataY[0].label,
        }}
      />
    ) : (
      <div className="d-flex w-100 h-100 justify-content-center">
        <img src={yoda} alt="Yoda dancing" />
      </div>
    );
  };

  const renderSideBar = () => {
    return (
      <Drawer
        onMouseEnter={() => handleSidebarOpen()}
        onMouseLeave={() => handleSidebarClose()}
        sx={{
          width: 10,
          flexShrink: 0,
          "& .MuiDrawer-paper": {
            width: "auto",
            marginTop: 7,
            height: "calc(100vh - 60px)",
            boxSizing: "border-box",
          },
        }}
        variant="persistent"
        anchor="left"
        open={showSidebar}
      >
        <div className={`${styles.userOptions}`}>
          <span className={styles.userSelectionTitle}>Select data to plot</span>
          {chartFamily === "Time/Trend Line" ? (
            <PlotDataSelectionList
              chartType={chartType}
              yearsRange={rangeOfYears}
              aggregateByYears={aggregateBy}
              selectedData={selectedData}
              setData={(newData) => setData(newData)}
              triggerPopUp={(message) => setShow(message)}
              isCorrelationPlot
            />
          ) : (
            <div className={`d-flex flex-column`}>
              {renderScatterDataListItems()}
            </div>
          )}
        </div>
      </Drawer>
    );
  };

  return (
    <div className={styles.mainContentContainer}>
      {renderToastMessage()}
      {renderSideBar()}
      {plotData.datasets.length ? (
        <Chart
          aggregateBy={aggregateBy}
          data={plotData}
          type={chartType as ChartType}
          aggregadedByYears={aggregateBy}
        />
      ) : (
        renderYodaOrScatter()
      )}
    </div>
  );
};

export default memo(MainContent);

interface MainContentProps {
  chartType: string;
  chartFamily: string;
  rangeOfYears: number[];
  aggregateBy: number;
}
