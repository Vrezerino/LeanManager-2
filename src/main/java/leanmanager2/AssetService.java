package leanmanager2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheConfig;

@Service
@CacheConfig(cacheNames = { "assets" })
public class AssetService {

    @Autowired
    AssetRepository assetRepository;

    @CacheEvict(key = "#asset.id", allEntries = true)
    public void save(Asset asset) {
        assetRepository.save(asset);
    }

    @Cacheable
    public List<Asset> findAll() {
        return assetRepository.findAll();
    }

    @CacheEvict(key = "#id")
    public void deleteById(Long id) {
        assetRepository.deleteById(id);
    }
}
