import { Kafka } from "kafkajs";

const setup = async () => {
  const kafka = new Kafka({
    clientId: "my-app",
    brokers: ["localhost:9092"],
  });
  return kafka.consumer({ groupId: "test-group" });
};

export const consumerRun = async () => {
  const consumer = await setup();
  await consumer.connect();
  await consumer.subscribe({ topic: "test-topic", fromBeginning: true });

  await consumer.run({
    eachMessage: async ({ topic, partition, message }) => {
      console.log({
        partition,
        offset: message.offset,
        value: message.value?.toString(),
      });
    },
  });
};

consumerRun().catch(console.error);
