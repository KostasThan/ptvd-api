import { Tooltip } from "@mui/material";
import React, { memo, useCallback, useEffect, useState } from "react";
import { Dropdown } from "react-bootstrap";
import { MDSApi } from "../../communication/mdsapi";
import Search from "./Search/Search";

export const DropDown: React.FunctionComponent<DropDownProps> = (props) => {
  const { title, items, setSelectedValue, isNavBar, isIndicatorsDropDown } =
    props;
  const [, setValue] = useState(title);
  const [search, setSearch] = useState("");

  const [filteredItems, setItems] = useState(items);
  useEffect(() => {
    setItems(items);
  }, [items]);

  const onSearchChange = useCallback(
    (value: string) => {
      setSearch(value);
      setItems(
        items.filter((item: any) => {
          return !isIndicatorsDropDown
            ? item.toLocaleLowerCase().includes(search.toLocaleLowerCase())
            : item.code
                .toLocaleLowerCase()
                .includes(search.toLocaleLowerCase());
        })
      );
    },
    [isIndicatorsDropDown, items, search]
  );

  const renderDropDownItem = (name: string) => (
    <Dropdown.Item
      key={name}
      className="me-2"
      onClick={() => {
        setSelectedValue(name);
        setValue(name);
      }}
    >
      {name}
    </Dropdown.Item>
  );

  return (
    <Dropdown className={` me-1`} navbar>
      <Dropdown.Toggle style={{ padding: isNavBar ? 4 : 0 }}>
        {title}
      </Dropdown.Toggle>

      <Dropdown.Menu
        style={{ maxHeight: "50vh", overflowX: "hidden", overflowY: "auto" }}
      >
        {!isNavBar && (
          <Dropdown.Header>
            <Search placeholder="Search" onChange={onSearchChange} />
          </Dropdown.Header>
        )}
        {filteredItems.map((name: any) => {
          return isIndicatorsDropDown ? (
            <Tooltip
              key={name.code}
              title={MDSApi.getIndicatorsName(filteredItems, name.code)}
              arrow
              placement="right"
            >
              {renderDropDownItem(name.code)}
            </Tooltip>
          ) : (
            renderDropDownItem(name)
          );
        })}
      </Dropdown.Menu>
    </Dropdown>
  );
};

interface DropDownProps {
  title: string;
  items: string[];
  setSelectedValue: (name: string) => void;
  isIndicatorsDropDown?: boolean;
  isCorrelationPlotSelected?: boolean;
  isNavBar?: boolean;
}

export default memo(DropDown);
