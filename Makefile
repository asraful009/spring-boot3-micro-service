

.PHONY: all config-server registry-server gateway-server common-lib organization-service help

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


common-lib:
	@cd common-lib && \
	 ./mvnw clean install

organization-service:
	@cd common-lib && \
	 ./mvnw clean install

# --- Show help ---
help:
	@echo ""
	@echo "Available commands:"
	@echo ""
	@echo "  make config-server             → Build the Run Config Server"
	@echo "  make registry-server           → Build the Run Registry Server"
	@echo "  make gateway-server            → Build the Run Gateway Server"
	@echo "  make common-lib                → Build the Run Common Lib"
	@echo ""
