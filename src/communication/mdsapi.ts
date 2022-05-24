import axios from "axios";

export class MDSApi {
  private static localDomain = "http://localhost:8080";
  private static remoteDomain = "https://ptvd-api.herokuapp.com";
  private static baseUrl = `${this.localDomain}/mds/`;

  public static getCountries(): Promise<any> {
    return axios.get(`${this.baseUrl}countries`);
  }

  public static getIndicators(): Promise<any> {
    return axios.get(`${this.baseUrl}indicators`);
  }

  public static getMetrics(
    country: string,
    indicatorCode: string,
    yearRange: number[],
    aggregateByYears: number
  ) {
    const aggregateByYearsParam = aggregateByYears
      ? `aggregateByYears=${aggregateByYears}&`
      : "";
    const url = `${this.baseUrl}metrics?${aggregateByYearsParam}countryName=${country}&fromYear=${yearRange[0]}&indicatorCode=${indicatorCode}&toYear=${yearRange[1]}`;

    return axios.get(url);
  }
  public static formatCounties(data: any): string[] {
    const countries: string[] = [];
    data.forEach((country: any) => {
      countries.push(country.name);
    });
    return countries;
  }

  public static getIndicatorCodes(data: any): string[] {
    const indicatorCodes: string[] = [];
    data.forEach((indicator: any) => {
      indicatorCodes.push(indicator.code);
    });
    return indicatorCodes;
  }

  public static getIndicatorsName(indicators: any, code: string): string {
    const indic = indicators.filter(
      (indicator: any) => indicator.code === code
    );
    return indic.length ? indic[0].name : "";
  }

  public static getIndicatorsDescription(
    indicators: any,
    code: string
  ): string {
    const indic = indicators.filter(
      (indicator: any) => indicator.code === code
    );

    return indic.length ? indic[0].description : "";
  }

  public static fomatDatas(datas: any, range: number[]) {
    const labels = this.getLabels(range);
    const finalDatas = new Array(labels.length).fill(null);
    datas.forEach((data: any) => {
      finalDatas[labels.indexOf(data.year.toString())] = data;
    });

    return finalDatas;
  }

  private static getLabels(rangeOfYears: number[]): string[] {
    const YaxisLabels = [];
    for (let index = rangeOfYears[0]; index < rangeOfYears[1]; index++) {
      YaxisLabels.push(index.toString());
    }
    return YaxisLabels;
  }
}
