package net.brunomendola.querity.demo.util;

import java.util.List;

public record Result<T>(List<T> items, Long totalCount) {
}
