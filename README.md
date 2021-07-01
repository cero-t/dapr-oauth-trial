# Dapr example
Say hello to Dapr with Java!

## Pre-request

Following tools should be installed.

1. Docker
2. Dapr v1.2
3. JDK (>=11)

## Usage

### 1. Run without OAuth

Start dapr
```
dapr run --app-id dapr-oauth-trial --app-port 8080 --dapr-http-port 18080 -- ./mvnw spring-boot:run
```

Open with browser
```
localhost:18080/v1.0/invoke/dapr-oauth-trial/method/hello
localhost:18080/v1.0/invoke/dapr-oauth-trial/method/metadata
```

And it shows
```
Hello, Dapr World
```
```json
{
  "id": "dapr-oauth-trial",
  "actors": [
    
  ],
  "extended": {
    "cliPID": "36848",
    "appCommand": "./mvnw spring-boot:run"
  },
  "components": [
    {
      "name": "pubsub",
      "type": "pubsub.redis",
      "version": "v1"
    },
    {
      "name": "statestore",
      "type": "state.redis",
      "version": "v1"
    }
  ]
}
```

### 2. Run with OAuth

Register a new GitHub App on your github page.

https://github.com/settings/apps/

- Homepage URL: ANY
- User authorization callback URL: http://localhost:18080
- Permissions: select nothing
- Where can this GitHub App be installed?: Any account

Copy clientId and clientSecret from your github app page to components/oauth.yaml
```yaml
    - name: clientId
      value: "<your client ID>"
    - name: clientSecret
      value: "<your client secret>"
```

Stop dapr and re-start with `--config` and `--components-path` option
```
(Ctrl-c)
dapr run --app-id dapr-oauth-trial --app-port 8080 --dapr-http-port 18080 --config ./config/oauth.yaml --components-path ./components -- ./mvnw spring-boot:run
```

Access the following page by web browser.

Open with browser
```
localhost:18080/v1.0/invoke/dapr-oauth-trial/method/hello
localhost:18080/v1.0/invoke/dapr-oauth-trial/method/metadata
```
