const treeData = [
  {
    id: 1,
    label: '建设部',
    children: [
      {
        id: 4,
        label: '工程局',
        children: [
          {
            id: 9,
            label: '综合公司'
          },
          {
            id: 10,
            label: '综合公司',
            children: [
              {
                id: 101,
                label: '公路建设公司'
              },
              {
                id: 102,
                label: '桥梁建设公司',
                children: [
                  {
                    id: 10201,
                    label: '一大队'
                  },
                  {
                    id: 10202,
                    label: '二大队'
                  },
                  {
                    id: 10203,
                    label: '三大队'
                  },
                  {
                    id: 10204,
                    label: '四大队'
                  }
                ]
              }
            ]
          }
        ]
      }
    ]
  },
  {
    id: 2,
    label: '空军',
    children: [
      {
        id: 201,
        label: '工程局'
      }
    ]
  }
]

export default treeData
