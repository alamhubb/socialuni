export default function dynamicImportTransform() {
  return {
    name: 'dynamic-import-transform',
    transform(code, id) {
      if (/\bdynamicImport\b/.test(code)) {
        const regex = /dynamicImport\(['"]([^'"]+)['"]\)/g;
        code = code.replace(regex, (match, path) => {
          const variableName = path.replace(/^.*?\/([^/]+)$/, '$1');
          return `await import('${variableName}/src/index.ts')`;
        });
      }
      return {
        code,
        map: null,
      };
    },
  };
}