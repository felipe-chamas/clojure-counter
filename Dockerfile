FROM clojure:temurin-21-tools-deps-jammy

WORKDIR /app

# Copy project files
COPY deps.edn shadow-cljs.edn ./
COPY src ./src
COPY resources ./resources

# Install Node.js for Shadow-CLJS
RUN apt-get update && apt-get install -y nodejs npm && rm -rf /var/lib/apt/lists/*

# Build frontend
RUN clojure -M:dev -m shadow.cljs.devtools.cli release app

# Expose port
EXPOSE 3000

# Run server
CMD ["clojure", "-M", "-m", "counter.server"]
