package Lab5.Collection;

import Lab5.Exceptions.InvalidArgument;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.*;

public class JsonCollectionLoader<T> implements CollectionLoader<T> {
    @Override
    public T load(String path, Class<? extends T> type) throws IOException {
        class LocalDateDeserializer implements JsonDeserializer<LocalDate> {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                String data = json.getAsString();
                return LocalDate.parse(data);
            }

        }
        class AddressDeserializer implements JsonDeserializer<Address> {
            @Override
            public Address deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                String data = json.getAsString();
                return new Address(data);
            }

        }

        class OrganizationTypeDeserializer implements JsonDeserializer<OrganizationType> {
            @Override
            public OrganizationType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                String data = json.getAsString();
                try {
                    return OrganizationType.getByName(data);
                } catch (InvalidArgument e) {
                    return null;
                }
            }
        }

        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException(String.format("%s : no such file", path));
        }

        if (!file.canRead()) {
            throw new AccessDeniedException(path, null, "permission to read denied");
        }

        try (BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            StringBuilder jsonString = new StringBuilder();
            String nextLine;
            while ((nextLine = input.readLine()) != null) {
                jsonString.append(nextLine);
            }
            Gson g = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                    .registerTypeAdapter(Address.class, new AddressDeserializer())
                    .registerTypeAdapter(OrganizationType.class, new OrganizationTypeDeserializer())
                    .create();

            T resultSet = g.fromJson(jsonString.toString(), type);
            return resultSet;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(String.format("%s : no such file", path));
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void save(String path, T collection) throws IOException {
        class LocalDateSerializer implements JsonSerializer<LocalDate> {
            @Override
            public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
                return new JsonPrimitive(src.toString());
            }
        }
        class AddressSerializer implements JsonSerializer<Address> {
            @Override
            public JsonElement serialize(Address src, Type typeOfSrc, JsonSerializationContext context) {
                return new JsonPrimitive(src.getZipCode());
            }
        }
        class OrganizationTypeSerializer implements JsonSerializer<OrganizationType> {
            @Override
            public JsonElement serialize(OrganizationType organizationType, Type typeOfT, JsonSerializationContext context) {
                return new JsonPrimitive(String.valueOf(organizationType));
            }
        }

        File file = new File(path);

        if (!file.exists()) {
            throw new FileNotFoundException(String.format("%s : no such file", path));
        }

        if (!file.canWrite()) {
            throw new AccessDeniedException(path, null, "permission to write denied");
        }

        try (BufferedWriter outputStreamWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                    .registerTypeAdapter(Address.class, new AddressSerializer())
                    .registerTypeAdapter(OrganizationType.class, new OrganizationTypeSerializer())
                    .setPrettyPrinting()
                    .create();
            String json = gson.toJson(collection);
            outputStreamWriter.write(json);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(String.format("%s : no such file", path));
        } catch (IOException e) {
            throw new IOException(e);
        }

    }
}
