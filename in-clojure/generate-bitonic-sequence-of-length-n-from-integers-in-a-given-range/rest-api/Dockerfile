FROM clojure:temurin-21-tools-deps-alpine

WORKDIR /app

COPY deps.edn .
RUN clj -P

COPY src/ src/

EXPOSE 3000

CMD ["clj", "-M", "-m", "api.core"]