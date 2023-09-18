package sangmyungdae.deliciousclimbing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sangmyungdae.deliciousclimbing.domain.entity.*;
import sangmyungdae.deliciousclimbing.domain.enums.Difficulty;
import sangmyungdae.deliciousclimbing.domain.enums.Region;
import sangmyungdae.deliciousclimbing.repository.AddressRepository;
import sangmyungdae.deliciousclimbing.repository.FamousMountainAddressRepository;
import sangmyungdae.deliciousclimbing.repository.FamousMountainRegionRepository;
import sangmyungdae.deliciousclimbing.repository.FamousMountainRepository;
import sangmyungdae.deliciousclimbing.util.ExceptionUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final FamousMountainRepository famousMountainRepository;
    private final AddressRepository addressRepository;
    private final FamousMountainAddressRepository famousMountainAddressRepository;
    private final FamousMountainRegionRepository famousMountainRegionRepository;

    private TbFamousMountain findFamousMountain(Long mountainId) {
        return famousMountainRepository.findById(mountainId)
                .orElseThrow(() -> ExceptionUtil.id(mountainId, TbFamousMountain.class.getName()));
    }

    private TbAddress findAddress(Long addressCode) {
        return addressRepository.findByCode(addressCode)
                .orElseThrow(() -> ExceptionUtil.id(addressCode, TbAddress.class.getName()));
    }

    @Transactional
    public List<Map<String, Object>> mappingFamousMountainAddress() throws IOException {
        // 추후 return 할 데이터 목록
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

        // return data key 목록
        List<String> headerList = new ArrayList<String>();

        try {
            ClassPathResource resource = new ClassPathResource("/csv/famousMountainAddress.csv");

            BufferedReader br = Files.newBufferedReader(Paths.get(resource.getURI()));
            String line = "";

            while ((line = br.readLine()) != null) {
                List<String> stringList = new ArrayList<String>();
                String stringArray[] = line.split(",");
                stringList = Arrays.asList(stringArray);

                // csv 1열 데이터를 header로 인식
                if (headerList.size() == 0) {
                    headerList = stringList;
                } else {
                    Map<String, Object> map = new HashMap<String, Object>();
                    // header 컬럼 개수를 기준으로 데이터 set
                    for (int i = 0; i < headerList.size(); i++) {
                        map.put(headerList.get(i), stringList.get(i));
                    }
                    mapList.add(map);
                }
            }

            for (Map<String, Object> item : mapList) {
                Long mountainId = Long.valueOf((String) item.get("mountain_id"));
                Long addressCode = Long.valueOf((String) item.get("address_code"));

                TbAddress address = findAddress(addressCode);
                TbFamousMountain famousMountain = findFamousMountain(mountainId);
                TbFamousMountainAddress famousMountainAddress = TbFamousMountainAddress.builder()
                        .address(address)
                        .famousMountain(famousMountain)
                        .build();

                famousMountainAddressRepository.save(famousMountainAddress);

            }

            return mapList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public List<Map<String, Object>> mappingFamousMountainRegion() throws IOException {
        // 추후 return 할 데이터 목록
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

        // return data key 목록
        List<String> headerList = new ArrayList<String>();

        try {
            ClassPathResource resource = new ClassPathResource("/csv/famousMountainRegion.csv");

            BufferedReader br = Files.newBufferedReader(Paths.get(resource.getURI()));
            String line = "";

            while ((line = br.readLine()) != null) {
                List<String> stringList = new ArrayList<String>();
                String stringArray[] = line.split(",");
                stringList = Arrays.asList(stringArray);

                // csv 1열 데이터를 header로 인식
                if (headerList.size() == 0) {
                    headerList = stringList;
                } else {
                    Map<String, Object> map = new HashMap<String, Object>();
                    // header 컬럼 개수를 기준으로 데이터 set
                    for (int i = 0; i < headerList.size(); i++) {
                        map.put(headerList.get(i), stringList.get(i));
                    }
                    mapList.add(map);
                }
            }

            for (Map<String, Object> item : mapList) {
                Region region = Region.valueOf((String) item.get(headerList.get(0)));
                Long mountainId = Long.valueOf((String) item.get(headerList.get(1)));

                TbFamousMountain famousMountain = findFamousMountain(mountainId);
                TbFamousMountainRegion famousMountainRegion = TbFamousMountainRegion.builder()
                        .region(region)
                        .famousMountain(famousMountain)
                        .build();

                famousMountainRegionRepository.save(famousMountainRegion);

            }

            return mapList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public List<Map<String, Object>> mappingFamousMountainEtc() throws IOException {
        // 추후 return 할 데이터 목록
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

        // return data key 목록
        List<String> headerList = new ArrayList<String>();

        try {
            ClassPathResource resource = new ClassPathResource("/csv/famousMountain_etc.csv");

            BufferedReader br = Files.newBufferedReader(Paths.get(resource.getURI()));
            String line = "";

            while ((line = br.readLine()) != null) {
                List<String> stringList = new ArrayList<String>();
                String stringArray[] = line.split(",");
                stringList = Arrays.asList(stringArray);

                // csv 1열 데이터를 header로 인식
                if (headerList.size() == 0) {
                    headerList = stringList;
                } else {
                    Map<String, Object> map = new HashMap<String, Object>();
                    // header 컬럼 개수를 기준으로 데이터 set
                    for (int i = 0; i < headerList.size(); i++) {
                        map.put(headerList.get(i), stringList.get(i));
                    }
                    mapList.add(map);
                }
            }

            for (Map<String, Object> item : mapList) {
                TbFamousMountain famousMountain = findFamousMountain(Long.valueOf((String) item.get(headerList.get(0))));
                famousMountain.setName((String) item.get(headerList.get(1)));
                famousMountain.setHeight(Integer.parseInt((String) item.get(headerList.get(2))));
                famousMountain.setPeriod((String) item.get(headerList.get(3)));
                famousMountain.setDifficulty(Difficulty.valueOfName((String) item.get(headerList.get(4))));
                famousMountain.setSeason((String) item.get(headerList.get(5)));

            }

            return mapList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public List<Map<String, Object>> mappingFamousMountainReason() throws IOException {
        // 추후 return 할 데이터 목록
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

        // return data key 목록
        List<String> headerList = new ArrayList<String>();

        try {
            ClassPathResource resource = new ClassPathResource("/csv/famousMountain_reason.csv");

            BufferedReader br = Files.newBufferedReader(Paths.get(resource.getURI()));
            String line = "";

            while ((line = br.readLine()) != null) {
                List<String> stringList = new ArrayList<String>();

                // csv 1열 데이터를 header로 인식
                if (headerList.size() == 0) {
                    String stringArray[] = line.split(",");
                    stringList = Arrays.asList(stringArray);
                    headerList = stringList;
                } else {
                    Map<String, Object> map = new HashMap<String, Object>();

                    stringList.add(line.substring(0,10));
                    stringList.add(line.substring(11));

                    // header 컬럼 개수를 기준으로 데이터 set
                    for (int i = 0; i < headerList.size(); i++) {
                        map.put(headerList.get(i), stringList.get(i));
                    }
                    mapList.add(map);
                }
            }

            for (Map<String, Object> item : mapList) {
                TbFamousMountain famousMountain = findFamousMountain(Long.valueOf((String) item.get(headerList.get(0))));
                famousMountain.setReason((String) item.get(headerList.get(1)));

            }

            return mapList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public List<Map<String, Object>> mappingFamousMountainInfo() throws IOException {
        // 추후 return 할 데이터 목록
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

        // return data key 목록
        List<String> headerList = new ArrayList<String>();

        try {
            ClassPathResource resource = new ClassPathResource("/csv/famousMountain_info.csv");

            BufferedReader br = Files.newBufferedReader(Paths.get(resource.getURI()));
            String line = "";

            while ((line = br.readLine()) != null) {
                List<String> stringList = new ArrayList<String>();

                // csv 1열 데이터를 header로 인식
                if (headerList.size() == 0) {
                    String stringArray[] = line.split(",");
                    stringList = Arrays.asList(stringArray);
                    headerList = stringList;
                } else {
                    Map<String, Object> map = new HashMap<String, Object>();

                    stringList.add(line.substring(0,10));
                    stringList.add(line.substring(11));

                    // header 컬럼 개수를 기준으로 데이터 set
                    for (int i = 0; i < headerList.size(); i++) {
                        map.put(headerList.get(i), stringList.get(i));
                    }
                    mapList.add(map);
                }
            }

            for (Map<String, Object> item : mapList) {
                TbFamousMountain famousMountain = findFamousMountain(Long.valueOf((String) item.get(headerList.get(0))));
                famousMountain.setInfo((String) item.get(headerList.get(1)));

            }

            return mapList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
