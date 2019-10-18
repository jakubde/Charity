package pl.coderslab.charity.utils;

import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectMapper {

    private final ModelMapper modelMapper;

    public ObjectMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <F, T> T convert(F from, Class<T> toClass) {
        return (T) modelMapper.map(from, toClass);
    }

    public <D, T> List<D> convertAll(final Collection<T> fromList, Class<D> outClass) {
        return fromList.stream()
                .map(from -> convert(from, outClass))
                .collect(Collectors.toList());
    }
}
