FROM gcc

ARG EXECUTION_PATH
ARG SOURCE_CODE_FILE_NAME
ARG INPUT

COPY ${EXECUTION_PATH}/${SOURCE_CODE_FILE_NAME} /app/
WORKDIR /app
RUN g++ -o start ${SOURCE_CODE_FILE_NAME}
ENTRYPOINT ["sh", "-c", "./start ${INPUT}"]
