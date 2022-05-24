# This is a sample Python script.
import os
import uuid
RELATIVE_PATH = '.\\data'


def is_float(element: str) -> bool:
    try:
        if '"' in element:
            float(element[1:-1])
        else:
            float(element)
        return True
    except ValueError:
        return False


def is_integer(element: str) -> bool:
    try:
        if '"' in element:
            int(element[1:-1])
        else:
            int(element)
        return True
    except ValueError:
        return False


def handle_metadata(metadata):

    codes_seen = set()

    out = open("indicators.txt", "w", encoding='UTF8')

    for c in metadata:
        t = open(c, "r", encoding='UTF8')
        t.readline() #skipping header

        line = t.readline()

        while line:
            indicator_code = line.split(",")[0]
            if indicator_code not in codes_seen:
                codes_seen.add(indicator_code)
                out.write("\"" + str(uuid.uuid4()) + "\"," + line.strip() + ",\r")
            line = t.readline()


def isNotHeaderLine(line):
    return not line.startswith("\"Country Name")


def handle_country_indicator_metrics(country_metrics_file):

    years_indexes_dick = dict()

    countries_out = open("countries.txt", "w", encoding='UTF8')

    for c in country_metrics_file:
        t = open(c, "r", encoding='UTF8')

        line = t.readline()

        #parse header
        while isNotHeaderLine(line):
            line = t.readline()

        # here we have the header line
        line_arr = line.split(",")
        for index, value in enumerate(line_arr):
            if is_integer(value):
                years_indexes_dick[index] = value

        line = t.readline()
        country = line.split(",")[0]
        countries_out.write(country + ",\r")
        metrics_out = open("metrics_" + country[1:-1] + ".txt", "w", encoding='UTF8')
        while line:
            indicator_code = line.split('","')[3]

            for index, val in enumerate(line.split("\",\"")):
                if is_float(val) or is_integer(val):
                    year = years_indexes_dick[index]
                    metrics_out.write('"{}","{}",{},{},\r'.format(str(uuid.uuid4()), indicator_code, val, year))

            line = t.readline()
        metrics_out.close()


def get_related_to_data_path():
    directory = os.getcwd()
    index = RELATIVE_PATH.index("\\")
    return directory + RELATIVE_PATH[index:] + '\\'


if __name__ == '__main__':

    files = [f for f in os.listdir(RELATIVE_PATH)]
    related_to_data_path = get_related_to_data_path()
    metadata = []
    country_metrics = []
    for f in files:
        # print(f)
        if f.startswith('Metadata'):
            metadata.append(related_to_data_path + f)
        else:
            country_metrics.append(related_to_data_path + f)

    handle_metadata(metadata)
    handle_country_indicator_metrics(country_metrics)



