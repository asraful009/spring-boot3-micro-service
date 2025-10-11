

.PHONY: all config-server registry-server gateway-server helps

config-server:
	@cd config-server && \
	 ./mvnw clean install && \
	 java -jar target/config-server-0.0.1.jar

registry-server:
	@cd registry-server && \
	 ./mvnw clean install && \
	 java -jar target/registry-server-0.0.1.jar


gateway-server:
	@cd gateway-server && \
	 ./mvnw clean install && \
	 java -jar target/gateway-server-0.0.1.jar

# --- Show help ---
help:
	@echo ""
	@echo "Available commands:"
	@echo "  make config-server        → Build the project (jar)"
	@echo "  make install      → Install jar into local Maven repository"
	@echo "  make run          → Run from packaged jar (builds first)"
	@echo "  make dev          → Run via Maven directly (development mode)"
	@echo "  make clean        → Clean target directory"
	@echo ""
	@echo "Optional variables:"
	@echo "  PROFILE=<profile>   (default: dev)"
	@echo "  PORT=<port>         (default: 8080)"
	@echo ""
	@echo "Example:"
	@echo "  make run PROFILE=prod PORT=9090"
	@echo ""
