# Kafka

# 🛍️ Apache Kafka Local Setup — Step-by-Step (macOS, Homebrew)

---

## 📦 Step 1: Install Kafka

```bash
brew install kafka
```

**Description:**
Installs Kafka 3.x and its CLI tools (`kafka-topics`, `kafka-console-producer`, etc.) via Homebrew.

**Note:** Kafka 3.x doesn't require Zookeeper anymore.

✅ Kafka installed

---

## 🧪 Step 2: Check Kafka Version

```bash
kafka --version
```

**Description:**
Confirms that Kafka CLI is installed and available in your `$PATH`.

✅ Should print something like: `Kafka 3.6.1`

---

## 🚀 Step 3: Start Kafka Server

### Option A: As background service (recommended)

```bash
brew services start kafka
```

Runs Kafka as a macOS launch agent (keeps running even after terminal closes).

---

### Option B: Run manually in terminal

```bash
kafka-server-start /opt/homebrew/etc/kafka/server.properties
```

Runs Kafka in foreground using the default configuration.

✅ Should log something like:

```
INFO [KafkaServer id=0] started (kafka.server.KafkaServer)
```

---

## 🔍 Step 4: Check if Kafka is Listening

```bash
lsof -i :9092
```

**Description:**
Lists processes listening on Kafka’s default port.

✅ Should show a `java` process listening on TCP `:9092`.

---

## 📁 Step 5: Create a Kafka Topic

```bash
kafka-topics --create \
  --topic test-topic \
  --bootstrap-server localhost:9092 \
  --partitions 1 \
  --replication-factor 1
```

**Description:**
Creates a Kafka topic named `test-topic` with 1 partition and 1 replica (sufficient for local development).

✅ Output:

```
Created topic test-topic.
```

---

## 📜 Step 6: List Topics to Verify

```bash
kafka-topics --list --bootstrap-server localhost:9092
```

**Description:**
Lists all Kafka topics to confirm `test-topic` was created.

✅ Output:

```
test-topic
```

---

## ✉️ Step 7: Start Kafka Producer

```bash
kafka-console-producer --topic test-topic --bootstrap-server localhost:9092
```

**Description:**
Launches a terminal prompt to send messages into the `test-topic`.

✅ Each line you type is published to Kafka.

---

## 📬 Step 8: Start Kafka Consumer

```bash
kafka-console-consumer --topic test-topic \
  --bootstrap-server localhost:9092 \
  --from-beginning
```

**Description:**
Reads all messages from `test-topic` from the beginning and displays them.

✅ You should see messages sent by the producer.

---

## 🧹 Extra (History Review)

```bash
man bat
```

Shows manual for the `bat` command (modern `cat` replacement).

```bash
bat --highlight-line 30:+10 ~/.zsh_history
```

Highlights specific lines in your shell history for inspection.

---
