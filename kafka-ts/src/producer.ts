import { Kafka } from "kafkajs";

const setupKafkaProducer = async () => {
  const kafka = new Kafka({
    clientId: "my-app",
    brokers: ["localhost:9092"],
  });
  return kafka.producer();
};

const producerRun = async () => {
  const producer = await setupKafkaProducer();
  await producer.connect();

  setInterval(async () => {
    await producer.send({
      topic: "test-topic",
      messages: [{ value: "Hello KafkaJS!" }],
    });
  }, 1000);
};

producerRun().catch(console.error);
