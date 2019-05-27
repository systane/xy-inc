package br.com.zup.api.configuration;


//@Configuration
//@EnableDynamoDBRepositories(basePackageClasses = IPoiRepository.class)
public class DynamoDBConfiguration {

   /* @Value("${amazon.dynamodb.endpoint}")
    private String dbEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String accessKey;

    @Value("${amazon.aws.secretkey}")
    private String secretKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB(){
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(awsCredentials());

        if(!StringUtils.isNullOrEmpty(dbEndpoint)){
            dynamoDBClient.setEndpoint(dbEndpoint);
        }

        return dynamoDBClient;
    }

    @Bean
    public AWSCredentials awsCredentials(){
        return new BasicAWSCredentials(accessKey, secretKey);
    }*/

//    public AWSCredentialsProvider amazonAWSCredentialsProvider() {
//        return new AWSStaticCredentialsProvider(amazonAWSCredentials());
//    }
//
//    @Bean
//    public AWSCredentials amazonAWSCredentials() {
//        return new BasicAWSCredentials(accessKey, secretKey);
//    }
//
//    @Bean
//    public DynamoDBMapperConfig dynamoDBMapperConfig() {
//        return DynamoDBMapperConfig.DEFAULT;
//    }
//
//    @Bean
//    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig config) {
//        return new DynamoDBMapper(amazonDynamoDB, config);
//    }
//
//    @Bean
//    public AmazonDynamoDB amazonDynamoDB() {
//        return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
//                .withRegion(Regions.US_EAST_1).build();
//    }
}
