export default function dynamicImportTransform() {
  return {
    name: 'dynamic-import-transform',
    transform(code, id) {
      const dynamicImportRegex = /PlatformModuleLoadUtil\.dynamicImport\(['"]([^'"]+)['"]\)/g;
      if (dynamicImportRegex.test(code)) {
        code = code.replace(dynamicImportRegex, "await import('$1-uni/src/index.ts')");
      }
      return {
        code,
        map: null,
      };
    },
  };
}
