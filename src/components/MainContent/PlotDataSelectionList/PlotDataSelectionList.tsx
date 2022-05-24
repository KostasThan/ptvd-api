import {
  faBook,
  faPen,
  faPlay,
  faPlus,
  faTrash,
  faUserCog,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Popover from "@mui/material/Popover";
import { memo, useCallback, useEffect, useState } from "react";
import { ColorUtils } from "../../../utils";
import { DataSelectionItem } from "../../../types";
import { DropDown, InfoModal } from "../../../components";
import { MDSApi } from "../../../communication/mdsapi";
import styles from "./PlotDataSelectionList.module.scss";

export const PlotDataSelectionList: React.FunctionComponent<
  PlotOptionsProps
> = (props) => {
  const {
    selectedData,
    setData,
    triggerPopUp,
    isCorrelationPlot,
    yearsRange,
    aggregateByYears,
    chartType,
  } = props;

  const [indicatorDescriptionModal, showModal] = useState({
    bd: "",
    title: "",
    show: false,
  });
  const [countries, setCountries] = useState<string[]>([]);
  const [indicators, setIndicators] = useState<string[]>([]);

  const [dataSelectionItems, setDataSelectionItems] = useState<
    DataSelectionItem[]
  >([{ id: 0, country: "Country", indic: "Indicator" }]);

  const [showPopover, setShowPopover] = useState(false);
  const [selectedItem, setSelectedItem] = useState<DataSelectionItem>();

  const [popoverTarget, setPopoverTarget] = useState<HTMLDivElement | null>(
    null
  );

  useEffect(() => {
    const countriesPromise = MDSApi.getCountries();
    const indicatorsPromise = MDSApi.getIndicators();
    countriesPromise.then((res) => {
      setCountries(MDSApi.formatCounties(res.data));
    });
    indicatorsPromise.then((res) => {
      setIndicators(res.data);
    });
  }, []);

  useEffect(() => {
    if (selectedData.length) {
      selectedData.map((data: any) =>
        isEdit(data.id, data.label.split(" ")[0], data.label.split(" ")[1])
      );
    }
  }, [aggregateByYears, yearsRange]);

  const getPlotIcon = (id: number) => {
    const existsInPlot = selectedData.filter((data: any) => data.id === id);
    return existsInPlot.length ? faPen : faPlay;
  };

  const fetchData = useCallback(
    (id: number, country: string, code: string, datasToBeEdited?: any) => {
      MDSApi.getMetrics(country, code, yearsRange, aggregateByYears).then(
        (res) => {
          if (datasToBeEdited) {
            const newDatas = selectedData.map((dt: any) => {
              if (dt.label === datasToBeEdited[0].label) {
                return {
                  ...dt,
                  label: `${country} ${code}`,
                  data: MDSApi.fomatDatas(res.data, yearsRange),
                };
              } else {
                return dt;
              }
            });

            setData(newDatas);
          } else {
            const chartColor = ColorUtils.getrandomColor();
            setData([
              ...selectedData,
              {
                id: id,
                label: `${country} ${MDSApi.getIndicatorsName(
                  indicators,
                  code
                )}`,
                data: MDSApi.fomatDatas(res.data, yearsRange),
                backgroundColor: chartColor,
                borderColor: chartColor,
              },
            ]);
          }
        }
      );
    },
    [selectedData, setData, yearsRange, aggregateByYears, indicators]
  );

  const handleIndicatorDescriptionModalShow = useCallback(() => {
    setShowPopover(false);

    showModal({
      bd: `${MDSApi.getIndicatorsDescription(
        indicators,
        selectedItem?.indic!
      )}`,
      title: `${MDSApi.getIndicatorsName(indicators, selectedItem?.indic!)}`,
      show: true,
    });
  }, [indicators, selectedItem?.indic]);

  const hasDefaultValues = (country: string, indicator: string): boolean => {
    return country === "Country" || indicator === "Indicator";
  };

  const validateDataSelection = useCallback(
    (c: string, i: string) => {
      if (chartType.includes("Select")) {
        triggerPopUp({
          show: true,
          message: `Please select a chart type first.`,
        });
        return true;
      }
      if (selectedData.length !== 0) {
        selectedData.forEach((data: any) => {
          if (data.label === `${c} ${i}`) {
            triggerPopUp({
              show: true,
              message: `${c}, ${i} already selected.`,
            });
          } else if (hasDefaultValues(c, i)) {
            triggerPopUp({ show: true, message: "No default values" });
          }
        });
      } else {
        if (hasDefaultValues(c, i)) {
          triggerPopUp({
            show: true,
            message:
              "You have selected the default values. Please select some values first.",
          });
        }
      }

      return selectedData.length !== 0
        ? selectedData.some(
            (data: any) => data.label === `${c} ${i}` || hasDefaultValues(c, i)
          )
        : hasDefaultValues(c, i);
    },
    [selectedData, triggerPopUp, chartType]
  );

  const isEdit = useCallback(
    (id: number, selectedCountry: string, selectedIndic: string) => {
      const datasToBeEdited = selectedData.filter(
        (data: any) => data.id === id
      );
      if (datasToBeEdited.length) {
        fetchData(id, selectedCountry, selectedIndic, datasToBeEdited);
        return true;
      }

      return false;
    },
    [selectedData, fetchData]
  );

  const addDataToPlot = useCallback(
    (id: number, c: string, i: string) => {
      if (!validateDataSelection(c, i)) {
        if (isEdit(id, c, i)) {
          return;
        }
        fetchData(id, c, i);
      }
    },
    [validateDataSelection, fetchData, isEdit]
  );

  const deleteDatasFromPlot = useCallback(() => {
    if (dataSelectionItems.length === 1) {
      const newSelection = { id: 0, country: "Country", indic: "Indicator" };
      setData([]);
      setDataSelectionItems([newSelection]);
    } else {
      setData(selectedData.filter((data: any) => data.id !== selectedItem?.id));
      setDataSelectionItems(
        dataSelectionItems.filter(
          (selection: DataSelectionItem) => selection.id !== selectedItem?.id
        )
      );
    }
  }, [dataSelectionItems, setData, selectedData, selectedItem?.id]);

  const renderDropDowns = (
    selectedCountry: string,
    selectedIndicator: string,
    id: number
  ) => (
    <>
      <DropDown
        title={selectedCountry}
        items={countries}
        setSelectedValue={(name) => {
          setDataSelectionItems(
            dataSelectionItems.map((userSelection: DataSelectionItem) => {
              if (userSelection.id === id) {
                return { ...userSelection, country: name };
              } else {
                return { ...userSelection };
              }
            })
          );
        }}
      />

      <DropDown
        title={selectedIndicator}
        items={indicators}
        setSelectedValue={(name) => {
          setDataSelectionItems(
            dataSelectionItems.map((selectionItem) => {
              if (selectionItem.id === id) {
                return { ...selectionItem, indic: name };
              } else {
                return { ...selectionItem };
              }
            })
          );
        }}
        isIndicatorsDropDown
      />
    </>
  );

  const renderUserSelectionItems = () =>
    dataSelectionItems.map((selectionItem: DataSelectionItem) => {
      return (
        <div
          className={`d-flex ms-2 mt-2 me-2 ${styles.userOptionItem}`}
          key={selectionItem.id}
        >
          {renderDropDowns(
            selectionItem.country,
            selectionItem.indic,
            selectionItem.id
          )}

          <div className="d-flex ms-auto">
            <FontAwesomeIcon
              className={`${styles.moreOptions} p-2 me-1`}
              role="button"
              onClick={() =>
                addDataToPlot(
                  selectionItem.id,
                  selectionItem.country,
                  selectionItem.indic
                )
              }
              icon={getPlotIcon(selectionItem.id)}
            />

            {renderOptions(selectionItem)}
          </div>
        </div>
      );
    });

  const renderOptions = (selectionItem: DataSelectionItem) => (
    <div
      role={"button"}
      className={styles.moreOptions}
      onClick={(e) => {
        setPopoverTarget(e.currentTarget);
        setSelectedItem(selectionItem);
        setShowPopover(!showPopover);
      }}
    >
      <FontAwesomeIcon icon={faUserCog} />
      <Popover
        open={showPopover}
        anchorEl={popoverTarget}
        onClose={() => setShowPopover(false)}
        anchorOrigin={{
          vertical: "bottom",
          horizontal: "left",
        }}
      >
        {renderLongDescriptionButton()}
        {renderDeleteButton()}
      </Popover>
    </div>
  );

  const renderLongDescriptionButton = () => {
    return (
      <div
        className={`${styles.moreOptionsItem} ${
          selectedItem?.indic === "Indicator" ? "pe-none text-muted" : ""
        }`}
        role="button"
        onClick={handleIndicatorDescriptionModalShow}
      >
        <FontAwesomeIcon icon={faBook} />
        <span className="ms-2">Description</span>
      </div>
    );
  };

  const renderDeleteButton = () => (
    <div
      className={`${styles.moreOptionsItem} ${styles.delete}`}
      role="button"
      onClick={() => {
        setShowPopover(false);
        deleteDatasFromPlot();
      }}
    >
      <FontAwesomeIcon icon={faTrash} />
      <span className="ms-2">Delete</span>
    </div>
  );

  const renderAddNewItemButton = () => (
    <div className="d-flex mt-2 me-2 mb-2">
      <FontAwesomeIcon
        className={`ms-auto mt-2 p-2 rounded-circle ${styles.addOptionBtn}`}
        icon={faPlus}
        role="button"
        onClick={() =>
          setDataSelectionItems([
            ...dataSelectionItems,
            {
              id: dataSelectionItems[dataSelectionItems.length - 1].id + 1,
              country: "Country",
              indic: "Indicator",
            },
          ])
        }
      />
    </div>
  );

  return (
    <>
      {renderUserSelectionItems()}

      <InfoModal
        handleClose={() => showModal({ bd: "", title: "", show: false })}
        modalBody={indicatorDescriptionModal.bd}
        showModal={indicatorDescriptionModal.show}
        modalTitle={indicatorDescriptionModal.title}
      />
      {isCorrelationPlot && renderAddNewItemButton()}
    </>
  );
};

interface PlotOptionsProps {
  selectedData: any;
  chartType: string;
  yearsRange: number[];
  aggregateByYears: number;
  setData: (newData: any) => void;
  triggerPopUp: (message: any) => void;
  isCorrelationPlot?: boolean;
}

export default memo(PlotDataSelectionList);
